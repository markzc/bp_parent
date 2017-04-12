package com.zc.bp.web.sysadmin;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.zc.bp.domain.Dept;
import com.zc.bp.exception.SysException;
import com.zc.bp.service.DeptService;
import com.zc.bp.utils.Page;
import com.zc.bp.web.BaseAction;

public class DeptAction extends BaseAction implements ModelDriven<Dept>{

	private Dept model = new Dept();
	@Override
	public Dept getModel() {
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
	
	private DeptService deptService;
	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}
	/**
	 * 分页显示
	 * @return
	 */
	public String list(){
		deptService.findPage("from Dept where state=1",page,Dept.class,null);
		
		page.setUrl("deptAction_list"); //相对路劲
		super.push(page);
		return "list";
	}
	
	/**
	 * 查看单个
	 */
	public String toview(){
		Dept dept = deptService.get(Dept.class, model.getId());
		super.push(dept);
		return "toView";
	}
	
	/**
	 * 转到增加页面
	 */
	public String tocreate(){
		List<Dept> deptList =  deptService.find("from Dept where state=1",Dept.class, null);
		ActionContext.getContext().getValueStack().set("deptList",deptList);
		return "tocreate";
	}
	/**
	 * 添加
	 */
	public String insert(){
		deptService.saveOrUpdate(model);
		return "insert";
	}
	/**
	 * 转到修改页面，数据回显
	 */
	public String toupdate(){
		Dept dept = deptService.get(Dept.class, model.getId());
		super.push(dept);
		
		//显示下拉列表
		List<Dept> deptList = deptService.find("from Dept where state=1",Dept.class, null);
		//去掉本身
		deptList.remove(dept);
		ActionContext.getContext().getValueStack().set("deptList", deptList);
		return "toupdate";
	}
	
	/**
	 * 修改
	 */
	public String update(){
		Dept dept = deptService.get(Dept.class, model.getId());
		
		//把修改了什么写上
		dept.setDeptName(model.getDeptName());
		dept.setParent(model.getParent());
		
		deptService.saveOrUpdate(dept);
		return "update";
	}
	
	/**
	 * 删除
	 * @throws SysException 
	 */
	public String delete() throws SysException{
		try {
			String[] idStrings = model.getId().split(", ");
			deptService.delete(Dept.class, idStrings);
			return "delete";
		} catch (Exception e) {
			e.printStackTrace();
			throw new SysException("先选中一个！！！");
		}
	}
}
