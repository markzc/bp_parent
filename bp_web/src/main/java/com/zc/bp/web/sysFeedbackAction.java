package com.zc.bp.web;


import com.opensymphony.xwork2.ModelDriven;
import com.zc.bp.domain.SysFeedback;
import com.zc.bp.domain.User;
import com.zc.bp.service.SysFeedbackService;
import com.zc.bp.utils.Page;

public class sysFeedbackAction extends BaseAction implements ModelDriven<SysFeedback> {
	//注入service
	private SysFeedbackService sysFeedbackService;
	public void setSysFeedbackService(SysFeedbackService sysFeedbackService) {
		this.sysFeedbackService = sysFeedbackService;
	}
	
	//model驱动
	private SysFeedback model = new SysFeedback();
	public SysFeedback getModel() {
		return this.model;
	}
	
	//作为属性驱动，接收并封装页面参数
	private Page page = new Page();			//封装页面的参数，主要当前页参数
	public void setPage(Page page) {
		this.page = page;
	}


	//普通用户反馈列表展示
	public String list(){
		User user =super.getCurUser();
		String hql = "from SysFeedback where 1=1";	
		
		if(user.getUserInfo().getDegree()==0){
			//管理员查询所有内容
			//给页面提供分页数据
			page.setUrl("sysFeedbackAction_adminlist");		//配置分页按钮的转向链接
			page = sysFeedbackService.findPage(hql, page, SysFeedback.class, null);
			super.put("page", page);
			
			return "adminlist";	
		}else{
			//普通员工管理自己信息
			hql+=" and createBy='"+user.getId()+"'";
			//查询所有内容
			//给页面提供分页数据
			page.setUrl("sysFeedbackAction_list");		//配置分页按钮的转向链接
			page = sysFeedbackService.findPage(hql, page, SysFeedback.class, null);
			super.put("page", page);
			
			return "list";						//page list	
		}
		
	}
	
	
	//普通用户新增系统反馈
	public String insert(){
		User user=super.getCurUser();
		model.setInputBy(user.getUserInfo().getName());
		System.out.println(model.getInputBy());
		model.setCreateBy(user.getId());
		model.setCreateDept(user.getUserInfo().getId());
		
		sysFeedbackService.saveOrUpdate(model);
		
		return "alist";			//返回列表，重定向action_list
	}

	//管理员反馈列表
	public String adminlist() throws Exception {
		
		String hql="from SysFeedback";
		//查询所有内容
		//给页面提供分页数据
		page.setUrl("sysFeedbackAction_adminlist");		//配置分页按钮的转向链接
		page = sysFeedbackService.findPage(hql, page, SysFeedback.class, null);
		super.put("page", page);
		
		return "adminlist";			
	}
	
	//管理员查看反馈
	public String toadminview(){
		SysFeedback obj = sysFeedbackService.get(SysFeedback.class, model.getId());
		super.push(obj);
		
		return "toadminview";			//转向查看页面
	}

	//进入系统反馈解决页面
	public String gotopage() throws Exception {
		SysFeedback obj = sysFeedbackService.get(SysFeedback.class, model.getId());
		
		super.push(obj);
		
		return "gotopage";
	}

	//保存
	public String adminsave(){
		User user=super.getCurUser();
		SysFeedback obj = sysFeedbackService.get(SysFeedback.class, model.getId());
		
		//设置修改的属性，根据业务去掉自动生成多余的属性
		obj.setAnswerBy(user.getUserInfo().getName());
		obj.setAnswerTime(model.getAnswerTime());
		obj.setResolution(model.getResolution());
		obj.setDifficulty(model.getDifficulty());
		obj.setState(1);
		
		sysFeedbackService.saveOrUpdate(obj);
		return "adminsave";
	}
	
	//普通用户进入反馈修改页面
	public String toupdate() throws Exception {
		SysFeedback obj = sysFeedbackService.get(SysFeedback.class, model.getId());
		super.push(obj);
		return "toupdate";
	}
	//保存普通用户修改的反馈
	public String update() throws Exception {
		SysFeedback obj = sysFeedbackService.get(SysFeedback.class, model.getId());
		//设置修改属性
		obj.setTitle(model.getTitle());
		obj.setContent(model.getContent());
		
		sysFeedbackService.saveOrUpdate(obj);
		return "update";
	}
	
	//删除一条
	public String deleteById(){
		sysFeedbackService.deleteById(SysFeedback.class, model.getId());
		
		return "alist";
	}
	
	
	//删除多条
	public String delete(){
		sysFeedbackService.delete(SysFeedback.class, model.getId().split(", "));
		
		return "alist";
	}
	
}
