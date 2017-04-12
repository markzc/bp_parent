package com.zc.bp.web.sysadmin;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.zc.bp.domain.Dept;
import com.zc.bp.domain.Module;
import com.zc.bp.domain.Role;
import com.zc.bp.domain.User;
import com.zc.bp.exception.SysException;
import com.zc.bp.service.DeptService;
import com.zc.bp.service.ModuleService;
import com.zc.bp.service.RoleService;
import com.zc.bp.service.UserService;
import com.zc.bp.utils.Encrypt;
import com.zc.bp.utils.Page;
import com.zc.bp.utils.SysConstant;
import com.zc.bp.utils.UtilFuns;
import com.zc.bp.web.BaseAction;

public class UserAction extends BaseAction implements ModelDriven<User>{

	private User model = new User();
	@Override
	public User getModel() {
		return model;
	}
	//分组插件
	private Page page = new Page();
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	private DeptService deptService;
	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}
	
	private RoleService roleService;
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	
	private String[] roleIds;
	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}
	
	/**
	 *分配角色
	 */
	public String role(){
		Set<Role> sets = new HashSet<Role>();
		for (String roleId : roleIds) {
			Role role = roleService.get(Role.class, roleId);
			sets.add(role);
		}
		User user = userService.get(User.class, model.getId());
		user.setRoles(sets);
		userService.saveOrUpdate(user);
		return "alist";
	}
	/**
	 * 转到分配角色
	 */
	public String torole(){
		User user = userService.get(User.class, model.getId());
		super.push(user);
		Set<Role> roles = user.getRoles();
		
		StringBuffer sb=new StringBuffer();
		for (Role role : roles) {
			sb.append(role.getName()).append(",");
		}
		super.put("roleStr", sb.toString());
		//查询所有的角色
		List<Role> roleList = roleService.find("from Role", Role.class, null);
		super.put("roleList",roleList);
		return "torole";
	}
	/**
	 * 分页显示
	 * @return
	 */
	public String list(){
		userService.findPage("from User where state=1 order by userInfo.orderNo",page,User.class,null);
		
		page.setUrl("userAction_list"); //相对路劲
		super.push(page);
		return "list";
	}
	
	/**
	 * 查看单个
	 */
	public String toview(){
		User user = userService.get(User.class, model.getId());
		super.push(user);
		return "toView";
	}
	
	/**
	 * 转到增加页面
	 */
	public String tocreate(){
		List<Dept> deptList =  deptService.find("from Dept where state=1",Dept.class, null);
		ActionContext.getContext().getValueStack().set("deptList",deptList);
		
		List<User> userList =  userService.find("from User where state=1",User.class, null);
		ActionContext.getContext().put("userList", userList);
		return "tocreate";
	}
	/**
	 * 添加
	 */
	public String insert(){
		userService.saveOrUpdate(model);
		return "alist";
	}
	/**
	 * 转到修改页面，数据回显
	 */
	public String toupdate(){
		User user = userService.get(User.class, model.getId());
		super.push(user);
		
		List<User> userList =  userService.find("from User where state=1",User.class, null);
		ActionContext.getContext().put("userList", userList);
		
		//显示下拉列表
		List<Dept> deptList =  deptService.find("from Dept where state=1",Dept.class, null);
		
		ActionContext.getContext().getValueStack().set("deptList", deptList);
		return "toupdate";
	}
		
	/**
	 * 修改
	 */
	public String update(){
		User obj = userService.get(User.class, model.getId());
		obj.setDept(model.getDept());
    	obj.setUsername(model.getUsername());
    	obj.setState(model.getState());
		obj.getUserInfo().setDegree(model.getUserInfo().getDegree());
		obj.getUserInfo().setManager(model.getUserInfo().getManager());
		userService.saveOrUpdate(obj);
		return "alist";
	}
	
	/**
	 * 删除
	 * @throws SysException 
	 */
	public String delete() throws SysException{
		try {
			String[] idStrings = model.getId().split(", ");
			userService.delete(User.class, idStrings);
			return "alist";
		} catch (Exception e) {
			e.printStackTrace();
			throw new SysException("先选中一个！！！");
		}
	}
	/**
	 * 查看个人资料
	 */
	public String sysuser(){
		User user = (User) session.get(SysConstant.CURRENT_USER_INFO);
		User user01 = userService.get( User.class,user.getId());
		super.push(user01);
		return "sysuser";
	}
	
	/**
	 * 修改账户信息
	 */
	public String edituser(){
		User user = (User) session.get(SysConstant.CURRENT_USER_INFO);
		User user01 = userService.get( User.class,user.getId());
		super.push(user01);
		return "edituser";
	}
	/**
	 * 保存账户信息
	 */
	public String saveuser(){
		User user = userService.get( User.class,model.getId());
		user.getUserInfo().setBirthday(model.getUserInfo().getBirthday());
		user.setUsername(model.getUsername());
		user.getUserInfo().setDegree(model.getUserInfo().getDegree());
		user.getUserInfo().setGender(model.getUserInfo().getGender());
		user.getUserInfo().setJoinDate(model.getUserInfo().getJoinDate());
		user.getUserInfo().setName(model.getUserInfo().getName());
		user.getUserInfo().setRemark(model.getUserInfo().getRemark());
		user.getUserInfo().setSalary(model.getUserInfo().getSalary());
		user.getUserInfo().setStation(model.getUserInfo().getStation());
		userService.saveOrUpdate(user);
		session.put(SysConstant.CURRENT_USER_INFO, user);
		return "userlist";
	}

	/**
	 * 去修改密码
	 */
	public String editPassword(){
		
		return "editPassword";
	}
	
	/**
	 * 修改
	 */
	public String updatePassword(){
		if( model.getPassword()==null || model.getPassword().equals("") ){
			request.put("err", "原密码错误！！！");
			return "editPassword";
		}
		if( newpassword == null || "".equals(newpassword) ){
			request.put("err", "新密码不能为空！！！");
			return "editPassword";
		}
		if( !repassword.equals(newpassword)){
			request.put("err", "两次密码不一致！！！");
			return "editPassword";
		}
		User user01 = (User) session.get(SysConstant.CURRENT_USER_INFO);
		List<User> find = userService.find("from User where username=? and password=?", User.class, new String[]{user01.getUsername(),Encrypt.md5(model.getPassword(), SysConstant.SALT)});
		if(UtilFuns.isNotEmpty(find)){
			User user02 = userService.get(User.class,user01.getId());
			user02.setPassword(Encrypt.md5(newpassword,SysConstant.SALT));
			userService.saveOrUpdate(user02);
			session.put(SysConstant.CURRENT_USER_INFO, user02);
		}else{
			request.put("err", "原密码错误！！！");
			return "editPassword";
		}
		return "userlist";
	}

	private String newpassword;
	private String repassword;
	public String getNewpassword() {
		return newpassword;
	}
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
	public String getRepassword() {
		return repassword;
	}
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	
	
}
