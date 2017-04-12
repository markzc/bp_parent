package com.zc.bp.web.sysadmin;

import com.opensymphony.xwork2.ModelDriven;
import com.zc.bp.domain.Module;
import com.zc.bp.exception.SysException;
import com.zc.bp.domain.Module;
import com.zc.bp.service.ModuleService;
import com.zc.bp.utils.Page;
import com.zc.bp.web.BaseAction;

public class ModuleAction extends BaseAction implements ModelDriven<Module>{

	private Module model=new Module();
	@Override
	public Module getModel() {
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
	
	private ModuleService moduleService;
	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}
	
	
	/**
	 * 分页显示
	 * @return
	 */
	public String list(){
		moduleService.findPage("from Module where state=1",page,Module.class,null);
		
		page.setUrl("moduleAction_list"); //相对路劲
		super.push(page);
		return "list";
	}
	
	/**
	 * 查看单个
	 */
	public String toview(){
		Module module = moduleService.get(Module.class, model.getId());
		super.push(module);
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
		moduleService.saveOrUpdate(model);
		return "insert";
	}
	/**
	 * 转到修改页面，数据回显
	 */
	public String toupdate(){
		Module module = moduleService.get(Module.class, model.getId());
		super.push(module);
		
		//显示下拉列表
		
		
		
		return "toupdate";
	}
		
	/**
	 * 修改
	 */
	public String update(){
		Module obj = moduleService.get(Module.class, model.getId());
		obj.setName(model.getName());
		obj.setLayerNum(model.getLayerNum());
		obj.setCpermission(model.getCpermission());
		obj.setCurl(model.getCurl());
		obj.setCtype(model.getCtype());
		obj.setState(model.getState());
		obj.setBelong(model.getBelong());
		obj.setCwhich(model.getCwhich());
		obj.setRemark(model.getRemark());
		obj.setOrderNo(model.getOrderNo());
		
		moduleService.saveOrUpdate(obj);
		return "update";
	}
	
	/**
	 * 删除
	 * @throws SysException 
	 */
	public String delete() throws SysException{
		
		try {
			String[] idStrings = model.getId().split(", ");
			moduleService.delete(Module.class, idStrings);
			return "delete";
		} catch (Exception e) {
			e.printStackTrace();
			throw new SysException("先选中一个！！！");
		}
	}

}
