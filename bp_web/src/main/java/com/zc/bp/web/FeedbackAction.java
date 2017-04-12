package com.zc.bp.web;

import java.util.List;

import com.opensymphony.xwork2.ModelDriven;
import com.zc.bp.domain.Dept;
import com.zc.bp.domain.Feedback;
import com.zc.bp.domain.User;
import com.zc.bp.service.DeptService;
import com.zc.bp.service.FeedbackService;
import com.zc.bp.service.UserService;
import com.zc.bp.utils.Page;

/**
 * @CreateDate:		2016-12-17 17:22:34
 * 
 * 用户反馈模块
 */

public class FeedbackAction extends BaseAction implements ModelDriven<Feedback> {
	//注入service
	private FeedbackService feedbackService;
	public void setFeedbackService(FeedbackService feedbackService) {
		this.feedbackService = feedbackService;
	}
	private DeptService deptService;
	
	public DeptService getDeptService() {
		return deptService;
	}
	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}
	//注入userService
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	//model驱动
	private Feedback model = new Feedback();
	public Feedback getModel() {
		return this.model;
	}
	
	//作为属性驱动，接收并封装页面参数
	private Page page = new Page();			//封装页面的参数，主要当前页参数
	public void setPage(Page page) {
		this.page = page;
	}
	public Page getPage(){
		return page;
	}


	//列表展示
	public String list(){
		//获取当前用户
		User user = super.getCurUser();
		String hql = "from Feedback where isShare = '1'";
		//细粒度权限控制
		if(user.getUserInfo().getDegree()==4){
			//普通员工--可以查看公开的和自己所写的反馈信息
			hql += " or createBy = '"+user.getId()+"'";
		}else if(user.getUserInfo().getDegree()==3 || user.getUserInfo().getDegree()==2 ){
			//部门经理--可以查看公开的和自己所写的反馈信息 反馈上级以及查看自己反馈
			hql += " or createBy = '"+user.getId()+"'";
			List<User> userList =  userService.find("from User where state=1",User.class, null);
			for (User user2 : userList) {
				if(user2.getUserInfo().getManager()!=null){
					if(user2.getUserInfo().getManager().getId().equals(user.getId())){
						hql += " or createBy = '"+user2.getId()+"'";
					}
				}
			}
			hql += " or createDept = '"+user.getDept().getId()+"'";
		}else if( user.getUserInfo().getDegree()==1){
			//本部门跟下属部门的信息
			hql += " or createBy = '"+user.getId()+"'";
			List<User> userList =  userService.find("from User where state=1",User.class, null);
			for (User user2 : userList) {
				if(user2.getUserInfo().getManager()!=null){
					if(user2.getUserInfo().getManager().getId().equals(user.getId())){
						hql += " or createBy = '"+user2.getId()+"'";
					}
				}
			}
			List<Dept> deptList =  deptService.find("from Dept where state=1",Dept.class, null);
			for (Dept dept : deptList) {
				if(dept.getParent()!=null){
					if(dept.getParent().getId().equals(user.getDept().getId())){
						hql += " or createDept = '"+dept.getId()+"'";
					}
				}
			}
			hql += " or createDept = '"+user.getDept().getId()+"'";
		}else{
			hql += " or 1 = 1";
		}
	
		
		//给页面提供分页数据
		page.setUrl("feedbackAction_list");		//配置分页按钮的转向链接
		page = feedbackService.findPage(hql, page, Feedback.class, null);
		//将页面存入值栈
		super.push(page);
		//将级别放入值栈传入页面,以便判断是否有下级
		super.put("degree",user.getUserInfo().getDegree());
		
		return "plist";						//page list
	}
	
	//转向新增页面
	public String tocreate(){
		
		return "pcreate";
	}
	
	//新增保存
	public String insert(){
		//获取当前用户
		User user = super.getCurUser();
		//设置当前用户名字,部门名称
		if(user.getUserInfo().getManager()!=null){
			//查询直属上级领导信息
			User manager = userService.get(User.class, user.getUserInfo().getManager().getId());
			//存入解决人的信息,默认是直属上级领导,但若后面解决人变化后期自行修改
			if(manager!=null){
				model.setAnswerBy(manager.getUserInfo().getName());
			}
		}
		//存入提出人信息,默认是当前用户
		model.setInputBy(user.getUserInfo().getName());
		
		//存入当前用户的id和部门id,便于细粒度权限控制
		model.setCreateBy(user.getId());
		model.setCreateDept(user.getDept().getId());
		
		feedbackService.saveOrUpdate(model);
		
		return "alist";			//返回列表，重定向action_list
	}

	//转向修改页面
	public String toupdate(){
		//获得当前用户
		User user = super.getCurUser();
		if(model.getId() != null && !"".equals(model.getId())){
			//获取当前反馈信息
			Feedback feedback = feedbackService.get(Feedback.class, model.getId());

			//获得反馈信息状态
			String state = feedback.getState();
			
			//判断是否已经处理,若没有处理则进行修改否则返回并提示.
			if("1".equals(state)){
				super.put("msg","该条反馈已经处理,您不能进行修改操作!");
				return "plist";
			}
			//未处理
			if("0".equals(state)){
				//判断是否有权限修改反馈信息
				//说明:只有自己或者直属领导或者级别在 0,1,2的领导才能修改反馈信息
				//第一次创建时,若为最高级没有预计默认处理人,所以需要判断
				if(feedback.getAnswerBy()!=null && !"".equals(feedback.getAnswerBy())){
					if(feedback.getInputBy().equals(user.getUserInfo().getName()) || feedback.getAnswerBy().equals(user.getUserInfo().getName()) || user.getUserInfo().getDegree() <= 2){
						//得到当前反馈信息提出人用户信息
						String name = feedback.getInputBy();
						//判断当前反馈信息是否为本人提出
						if(name.equals(user.getUserInfo().getName())){
							super.put("isCurUser", "yes");
						}
						super.push(feedback);
						return "pupdate";
					}else{
						super.put("msg","该条反馈您无权修改!");
						return "plist";
					}
				}else{//表示当前创建的反馈信息人没有直属上级
					if(feedback.getInputBy().equals(user.getUserInfo().getName()) || user.getUserInfo().getDegree() <= 2){
						//得到当前反馈信息提出人用户信息
						String name = feedback.getInputBy();
						
						//判断当前反馈信息是否为本人提出
						if(name.equals(user.getUserInfo().getName())){
							super.put("isCurUser", "yes");
						}
						super.push(feedback);
						return "pupdate";
					}else{
						super.put("msg","该条反馈您无权修改!");
						return "plist";
					}
				}
				
			}
			return NONE;
		}else{
			super.put("msg","您没有选择任何一项,无法进行操作!");
			return "plist";
		}
		
		
	}
	
	//修改保存
	public String update(){
		//获取当前反馈信息
		Feedback feedback = feedbackService.get(Feedback.class, model.getId());
		
		//设置修改的属性，根据业务去掉自动生成多余的属性
		feedback.setInputTime(model.getInputTime());
		feedback.setTitle(model.getTitle());
		feedback.setContent(model.getContent());
		feedback.setClassType(model.getClassType());
		feedback.setTel(model.getTel());
		feedback.setIsShare(model.getIsShare());
		
		feedbackService.saveOrUpdate(feedback);
		
		return "alist";
	}
	
	//删除一条
	public String deleteById(){
		//获取当前用户
		User user = super.getCurUser();
		
		//得到要删除的反馈信息
		Feedback feedback = feedbackService.get(Feedback.class, model.getId());
		
		//获得反馈信息状态
		String state = feedback.getState();
		
		//未处理
		if("0".equals(state)){
			super.put("msg","该条反馈还未处理,不能删除!");
			return "underList";
		}
		
		//说明已处理
		if("1".equals(state)){
			//当存在默认的处理人时
			if(feedback.getAnswerBy() != null && !"".equals(feedback.getAnswerBy())){
				//判断是否有权限修改反馈信息
				//说明:只有自己或者直属领导或者级别在 0,1,2的领导才能删除反馈信息
				if(feedback.getInputBy().equals(user.getUserInfo().getName()) || feedback.getAnswerBy().equals(user.getUserInfo().getName()) || user.getUserInfo().getDegree() <= 2){
					feedbackService.deleteById(Feedback.class, model.getId());
					return "ulist";
				}else{
					super.put("msg","您没有权限进行相关操作!");
					return "underList";
				}
			}else{//不存在默认处理人
				//判断是否有权限修改反馈信息
				//说明:只有自己或者直属领导或者级别在 0,1,2的领导才能删除反馈信息
				if(feedback.getInputBy().equals(user.getUserInfo().getName()) || user.getUserInfo().getDegree() <= 2){
					feedbackService.deleteById(Feedback.class, model.getId());
					return "ulist";
				}else{
					super.put("msg","您没有权限进行相关操作!");
					return "underList";
				}
			}
			
		}
		return NONE;
		
 	}
	
	
	//删除多条
	public String delete(){
		//获取当前用户
		User user = super.getCurUser();
		//判断是否选择一项进行操作
		if(model.getId() != null && !"".equals(model.getId())){
			//将获取到的id值分离成数组
			String[] ids = model.getId().split(", ");
			//定义两个变量,分别代表循环中存在未处理的和没有权限处理的反馈信息数
			int i = 0,j = 0;
			//循环遍历
			for (String id : ids) {
				//依次获得每个对象并判断
				Feedback feedback = feedbackService.get(Feedback.class, id);
				String state = feedback.getState();
				//当状态处于已处理状态时,i+1
				if("0".equals(state)){
					i++;
				}
				//当存在默认的处理人时
				if(feedback.getAnswerBy() != null && !"".equals(feedback.getAnswerBy())){
					if(!feedback.getInputBy().equals(user.getUserInfo().getName()) && !feedback.getAnswerBy().equals(user.getUserInfo().getName()) && user.getUserInfo().getDegree() >= 3){
						j++;
					}
				}else{//不存在默认处理人
					if(! feedback.getInputBy().equals(user.getUserInfo().getName()) &&  user.getUserInfo().getDegree() >= 3){
						j++;
					}
				}
			}
			//判断是否有未处理的信息
			if(i > 0){
				super.put("msg","您所选的信息中有"+i+"条未处理,不能删除!");
				return "plist";
			}
			//判断是否有没有权限删除的信息
			if(j > 0){
				super.put("msg","您没有权限删除其中的"+j+"条信息!");
				return "plist";
			}
			feedbackService.delete(Feedback.class, ids);
			return "alist";
		}else{
			super.put("msg","您没有选择任何一项,无法进行正常操作!");
			return "plist";
		}
		
	}
	
	//查看当前列表
	public String toview(){
		if(model.getId() != null){
			String[] ids = model.getId().split(", ");
			if(ids.length>1){
				super.put("msg","您选择错误,无法进行正常操作!");
				return "plist";
			}
			Feedback obj = feedbackService.get(Feedback.class, model.getId());
			super.push(obj);
			return "pview";	
		}else{
			super.put("msg","您选择错误,无法进行正常操作!");
			return "plist";
		}
	}
	/*//查看下级列表
	public String tounderView(){
		if(model.getId() != null){
			String[] ids = model.getId().split(", ");
			if(ids.length>1){
				super.put("msg","您选择错误,无法进行正常操作!");
				return "ulist";
			}
			Feedback obj = feedbackService.get(Feedback.class, model.getId());
			super.push(obj);
			return "uview";	
		}else{
			super.put("msg","您选择错误,无法进行正常操作!");
			return "ulist";
		}
	}*/
	
	//查看下级反馈
	public String tounderling() throws Exception {
		//获取当前用户
		User user = super.getCurUser();
		//查看下级的反馈列表
		String hql = "from Feedback where createDept ='"+user.getDept().getId()+"' and createBy != '"+user.getId()+"'";
		page = feedbackService.findPage(hql, page, Feedback.class, null);
		page.setUrl("feedbackAction_tounderling");
		super.push(page);
		return "underList";
	}
	
	//上级答复下级
	public String toanswer() throws Exception {
		//答复下级反馈,进入页面进行解决
		Feedback feedback = feedbackService.get(Feedback.class, model.getId());
		//获取用户列表
		List<User> userList = userService.find("from User where state = 1", User.class, null);
		super.put("userList", userList);
		//由于解决反馈的人可能变化,此处设置当前用户为解决该反馈的人
		User user = super.getCurUser();
		feedback.setAnswerBy(user.getUserInfo().getName());
		super.push(feedback);
		return "toanswer";
	}
	
	//保存修改后的反馈信息,并反馈给用户
	public String answer() throws Exception{
		Feedback feedback = feedbackService.get(Feedback.class, model.getId());
		
		//设置给下级的反馈信息,并修改状态为已处理状态
		feedback.setClassType(model.getClassType());
		feedback.setAnswerBy(model.getAnswerBy());
		feedback.setAnswerTime(model.getAnswerTime());
		feedback.setSolveMethod(model.getSolveMethod());
		feedback.setResolution(model.getResolution());
		feedback.setDifficulty(model.getDifficulty());
		feedback.setIsShare(model.getIsShare());
		feedback.setState("1");
		
		feedbackService.saveOrUpdate(feedback);
		
		return tounderling();
	}
	
}
