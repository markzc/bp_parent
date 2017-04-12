package com.zc.bp.web.sysadmin;



import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;
import com.zc.bp.domain.Module;
import com.zc.bp.domain.Role;
import com.zc.bp.exception.SysException;
import com.zc.bp.service.ModuleService;
import com.zc.bp.service.RoleService;
import com.zc.bp.utils.Page;
import com.zc.bp.web.BaseAction;

public class RoleAction extends BaseAction implements ModelDriven<Role>{

	private Role model = new Role();
	@Override
	public Role getModel() {
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
	
	private RoleService roleService;
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	
	
	/**
	 * 分页显示
	 * @return
	 */
	public String list(){
		roleService.findPage("from Role",page,Role.class,null);
		page.setUrl("roleAction_list"); //相对路劲
		super.push(page);
		return "list";
	}
	
	/**
	 * 查看单个
	 */
	public String toview(){
		Role role = roleService.get(Role.class, model.getId());
		super.push(role);
		return "toView";
	}
	
	/**
	 * 转到增加页面
	 */
	public String tocreate(){
		
		
		
		return "tocreate";
	}
	/**
	 * 添加
	 */
	public String insert(){
		roleService.saveOrUpdate(model);
		return "alist";
	}
	/**
	 * 转到修改页面，数据回显
	 */
	public String toupdate(){
		Role role = roleService.get(Role.class, model.getId());
		super.push(role);
		
		//显示下拉列表
		
		
		
		return "toupdate";
	}
		
	/**
	 * 修改
	 */
	public String update(){
		Role obj = roleService.get(Role.class, model.getId());
		obj.setName(model.getName());
		obj.setRemark(model.getRemark());
		roleService.saveOrUpdate(obj);
		return "alist";
	}
	
	/**
	 * 删除
	 */
	public String delete()throws Exception{
		try {
			String[] idStrings = model.getId().split(", ");
			roleService.delete(Role.class, idStrings);
			return "alist";
		} catch (Exception e) {
			e.printStackTrace();
			throw new SysException("先选中一个！！！");
		}
	}
	
	/**
	 * 进入到模块分配页面
	 */
	public String tomodule() throws Exception {
	  //1.根据角色id,得到角色对象
	  Role role = roleService.get(Role.class, model.getId());
	  //2.将角色对象放入值栈中
	  super.push(role);
	  //3.跳页面
	  return "tomodule";
	}
	
	public String loadJSONTreeNodes() throws Exception {
		  //1.根据角色 id加载角色对象
		  Role obj = roleService.get(Role.class, model.getId());
		  //2.对象导航，得到当前这个角色所具有的模块列表
		  Set<Module> moduleSet = obj.getModules();
		  //3.加载数据库中所有的模块列表
		  List<Module> moduleList = moduleService.find("from Module where state=1", Module.class, null);
		  //4.拼接JSON串
		  StringBuilder sb = new StringBuilder();
		  sb.append("[");
		  int size=moduleList.size();//得到集合中元素的个数
		  for(Module module :moduleList){
		    size--;
		    //遍历出每个模块
		    sb.append("{\"id\":\"").append(module.getId());
		    sb.append("\",\"pId\":\"").append(module.getParentId());
		    sb.append("\",\"name\":\"").append(module.getName());
		    sb.append("\",\"checked\":\"");
		    if(moduleSet.contains(module)){
		      //当前用户具有的这个模块
		      sb.append("true");
		    }else{
		      sb.append("false");
		    }
		    sb.append("\"}");
		    if(size>0){
		      sb.append(","); //当size=0时，说明集合中没有其它元素要遍历了，此时的元素是最后一个元素，后面不能添加逗号了
		    }
		  }
		  sb.append("]");
		  //输出
		  HttpServletResponse response = ServletActionContext.getResponse();
		  //设置编码
		  response.setContentType("application/json;charset=UTF-8");//json数据的mime类型：application/json
		  response.setHeader("Cache-Control", "no-cache");//设置响应消息头，没有缓存
		  response.getWriter().write(sb.toString());//向客户端输出
		  return NONE;
		}
	private ModuleService moduleService;
	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}
	/**
	* 实现模块分配
	* <input type="hidden" name="id" value="${id}"/>
	<input type="hidden" id="moduleIds" name="moduleIds" value="" />
	*/
	public String module() throws Exception {
	  //1.根据角色id,得到角色对象
	  Role role = roleService.get(Role.class, model.getId());
	  //2.切割模块的id字符串
	  String []mids = moduleIds.split(",");//模块id的数组
	  //创建一个模块列表的集合
	  Set<Module> moduleSet = new HashSet<Module>();
	  //3.遍历每个模块的id
	  for(String id :mids){
	    Module m = moduleService.get(Module.class, id);//加载出模块对象
	    moduleSet.add(m);//将加载出来的模块对象，放入模块列表中
	  }
	  //3.设置角色与模块的关系
	  role.setModules(moduleSet);
	  //4.保存角色与模块的关系
	  roleService.saveOrUpdate(role);
	  //5.跳页面
	  return "alist";
	}
	
	private String moduleIds;
	public void setModuleIds(String moduleIds) {
		this.moduleIds = moduleIds;
	}
	
}
