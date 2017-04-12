package com.zc.bp.web.sysadmin;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ModelDriven;
import com.zc.bp.domain.Module;
import com.zc.bp.domain.Task;
import com.zc.bp.domain.User;
import com.zc.bp.service.ContractProductService;
import com.zc.bp.service.ModuleService;
import com.zc.bp.service.TaskService;
import com.zc.bp.service.UserService;
import com.zc.bp.utils.Page;
import com.zc.bp.utils.UtilFuns;
import com.zc.bp.web.BaseAction;
/**
 * 代办任务模块
 * @author zc
 *
 */
public class TaskAction extends BaseAction implements ModelDriven<Task> {

	private Task model = new Task();

	@Override
	public Task getModel() {
		// TODO Auto-generated method stub
		return model;

	}

	public void setModel(Task model) {
		this.model = model;
	}

	// 分页组件
	private Page page = new Page();

	public void setPage(Page page) {
		this.page = page;
	}

	public Page getPage() {
		return page;
	}

	// 注入service

	private TaskService taskService;

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	private ModuleService moduleService;

	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}
	private UserService userService;
	
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	private ContractProductService contractProductService;

	public void setContractProductService(ContractProductService contractProductService) {
		this.contractProductService = contractProductService;
	}

	public String list() {
		String hql = "from Task where 1=1";
		// 获取当前登录的用户信息
		User user = super.getCurUser();
		// 查询当前登录的用户的等级
		Integer degree = user.getUserInfo().getDegree();
		if (degree == 4) {
			// 普通员工
			hql += " and user.id ='" + user.getId() + "'";
		} else if (degree == 3) {
			// 部门经理
			hql += " and createDept = '" + user.getDept().getId() + "' or user.id='"+user.getId()+"' or createBy ='"+user.getId()+"'";
		} else if (degree == 2) {
			// 管理部门及下属部门

			hql += " and createDept like '" + user.getDept().getId() + "%' or user.id='"+user.getId()+"' or createBy ='"+user.getId()+"'";

		} else {
			hql += " or 1=1";
		}

		taskService.findPage(hql, page, Task.class, null);
		page.setUrl("taskAction_list");
		super.push(page);
		List<Task> results = page.getResults();
		
		Set<User> userList = new HashSet<User>();
		for (Task task : results) {
			User u = userService.get(User.class, task.getCreateBy());
			userList.add(u);
		}
		for (Task task : results) {
			if(task.getUpdateBy()!=null){
				User u = userService.get(User.class, task.getUpdateBy());
				task.setUpdateBy(u.getUsername());
			}
			
		}
		
		super.put("createUserList", userList);
	
		return "list";
	}

	/**
	 * 查询当前id的详情
	 * 
	 * @return
	 */
	public String toview() {
		System.out.println(model.getId());
		Task obj = taskService.get(Task.class, model.getId());
		User curUser = super.getCurUser();
		if(curUser.getId().equals(obj.getUser().getId())){
			obj.setDefaultState(1);//已查看
		}
		
		super.push(obj);
		
		List<User> createUserList = userService.find("from User where state=1 and id='"+obj.getCreateBy()+"' ", User.class,null);
		if(UtilFuns.isNotEmpty(obj.getUpdateBy())){
			User updateUser = userService.get(User.class, obj.getUpdateBy());
			super.push(updateUser);
		}
		super.put("createUserList",createUserList);
		
		
		System.out.println(obj.getId());
		return "toview";
	}

	/**
	 * 进入添加页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String tocreate() throws Exception {

		List<Module> moduleList = moduleService.find("from Module where state=1", Module.class, null);

		super.put("moduleList", moduleList);

		return "tocreate";
	}

	/**
	 * 添加部门
	 * 
	 * @return
	 */
	public String insert() {

		User user = super.getCurUser();
		model.setCreateBy(user.getId());
		model.setCreateDept(user.getDept().getId());
		System.out.println(model.getUser().getId());
		User user2 = userService.get(User.class, model.getUser().getId());
		model.setUser(user2);

		taskService.saveOrUpdate(model);

		return "alist";
	}

	/**
	 * 进入修改页面
	 * 
	 * @return
	 */
	public String toupdate() {
		List<Module> moduleList = moduleService.find("from Module where state=1", Module.class, null);

		super.put("moduleList", moduleList);
		Task obj = taskService.get(Task.class, model.getId());
		User user = super.getCurUser();
		List<User> userList = taskService.findUser(model,obj.getModule().getId(),user);
		super.put("userList", userList);
		super.push(obj);

		return "toupdate";
	}

	/**
	 * 修改的方法
	 * 
	 * @return
	 */
	/**
	 * 获得修改之前的 接收人的 id
	 * @return
	 */
	private String userId;
	
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String update() {
		Task obj = taskService.get(Task.class, model.getId());
		Module module = moduleService.get(Module.class, model.getModule().getId());
		obj.setModule(module);
		
		User user = userService.get(User.class, model.getUser().getId());
		obj.setUser(user);
		obj.setCreateTime(model.getCreateTime());
		obj.setFinishDate(model.getFinishDate());
		if(UtilFuns.isNotEmpty(model.getUpdateBy())){
			obj.setUpdateBy(model.getUpdateBy());
			obj.setUpdateTime(new Date());
		}
		
		
		obj.setDescription(model.getDescription());
		
		taskService.saveOrUpdate(obj);
		/*taskService.updateTask(obj,userId);*/
		return "alist";
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	public String delete() {

		// 1.得到用户所选中的id数组
		String ids[] = model.getId().split(", ");

		// 2.调用业务方法，实现删除操作
		taskService.delete(Task.class, ids);

		return "alist";
	}

	/**
	 * 查出当前模块的 所有 能够负责的人的方法 select * from user_p u1 ,user_info_p u2 where
	 * u1.user_id in ( select user_id from role_user_p where role_id in (select
	 * role_id from role_module_p where module_id='501')) and u2.degree in(3,4);
	 */
	private String modId;
	
	

	public void setModId(String modId) {
		this.modId = modId;
	}

	public String choice() throws IOException {
		User user = super.getCurUser();
		
		
		List<User> userList=taskService.findUser(model,modId,user);
		System.out.println(userList);
	
		String jsonString = JSON.toJSONString(userList);
		System.out.println(jsonString);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");//设置响应头  没有缓存
		response.getWriter().write(jsonString);
		return NONE;
	}
	/**
	 * 转交任务的方法
	 * @return
	 */
	public String toTransmit(){
		List<Module> moduleList = moduleService.find("from Module where state=1", Module.class, null);

		super.put("moduleList", moduleList);
		
		Task obj = taskService.get(Task.class, model.getId());
		User user = super.getCurUser();
		List<User> userList = taskService.findUser(model,obj.getModule().getId(),user);
		userList.remove(obj.getUser());
		super.put("userList", userList);
		super.push(obj);
		/*if(obj.getUpdateBy()!=null){
			User updateUser = userService.get(User.class, obj.getUpdateBy());
			super.push(updateUser);
		}*/
		List<User> updateUserList = userService.find("from User where id='"+obj.getUpdateBy()+"'", User.class, null);
		super.put("updateUserList", updateUserList);
		return "toTransmit";
	}
	
	public String transmit(){
		User currentUser = getCurUser();
		Task obj= taskService.get(Task.class, model.getId());
		User user = userService.get(User.class, model.getUser().getId());
		
		obj.setDefaultState(0);
		obj.setState(2);
		obj.setUpdateTime(new Date());
		
		/*if("".equals(obj.getUpdateBy())||obj.getUpdateBy()==null){*/
			obj.setUser(user);
		/*}else{
			obj.setUser(user);
			obj.setUpdateBy(null);
			obj.setState(3);
		}*/
		obj.setUpdateBy(currentUser.getId());
		taskService.saveOrUpdate(obj);
		
		return "alist";
	}
	
	/**
	 * 提交（批量） <input type="checkbox" name="id" value=
	 * "8a7e84215827fe1b015828058c360001"/> model:
	 * id属性：8a7e84215827fe1b015828058c360001, 8a7e84215827fe1b015828058c360001,
	 * 8a7e84215827fe1b015828058c360001
	 */
	public String submit() throws Exception {
		changeState(1);
		
		return "alist";
	}

	/**
	 * 取消（批量）
	 */
	public String cancel() throws Exception {
		changeState(0);

		return "alist";
	}

	// 修改状态的方法
	private void changeState(int state) {
		// 1.处理用户选择的复选框中的id
		String ids[] = model.getId().split(", ");

		// 2.遍历数组，得到每个购销合同的id,再修改购销合同对象的状态
		for (String id : ids) {
			// 根据每个购销合同的id,加载出购销合同的对象
			Task obj = taskService.get(Task.class, id);
			obj.setDefaultState(1);
			// 修改obj对象的状态
			obj.setState(state);

			taskService.saveOrUpdate(obj);// 保存修改的结果
		}
	}
	
	/**
	 * 进入返还任务页面
	 */

	public String toRefund(){
		
		User curUser = super.getCurUser();
		taskService.findPage("from Task where state =2 and user.id='"+curUser.getId()+"'", page,Task.class, null);
		
		super.push(page);
		List<Task> results = page.getResults();
		
		Set<User> userList = new HashSet<User>();
		for (Task task : results) {
			User u = userService.get(User.class, task.getCreateBy());
			userList.add(u);
		}
		for (Task task : results) {
			if(task.getUpdateBy()!=null){
				User u = userService.get(User.class, task.getUpdateBy());
				task.setUpdateBy(u.getUsername());
			}
			
		}
		
		super.put("createUserList", userList);
		return "toRefund";
	}
	/**
	 * 返还任务
	 */
	public String refund(){
		
		Task task = taskService.get(Task.class, model.getId());
		//查找出修改人
		User updateUser = userService.get(User.class, task.getUpdateBy());
		task.setUser(updateUser);
		User curUser = super.getCurUser();
		task.setUpdateBy(curUser.getId());
		task.setState(3);
		task.setUpdateTime(new Date());
		task.setDefaultState(0);//设置成新任务
		taskService.saveOrUpdate(task);
		return "alist";
	}
}
