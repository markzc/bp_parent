package com.zc.bp.web;

import java.util.List;


import com.opensymphony.xwork2.ModelDriven;
import com.zc.bp.domain.Factory;
import com.zc.bp.domain.User;
import com.zc.bp.domain.UserInfo;
import com.zc.bp.service.FactoryService;
import com.zc.bp.service.UserService;
import com.zc.bp.utils.Page;
import com.zc.bp.utils.SysConstant;
import com.zc.bp.utils.UtilFuns;


public class BaseinfoAction extends BaseAction implements ModelDriven<Factory>{

	private FactoryService factoryService;
	
	
	public FactoryService getFactoryService() {
		return factoryService;
	}
	public void setFactoryService(FactoryService factoryService) {
		this.factoryService = factoryService;
	}
	/**
	 * 转到基础信息页面
	 * @return
	 */
	public String code(){
		return "code";
	}
	/**
	 * 分页查询厂家信息
	 * @return
	 */
	private Page<Factory> page = new Page<Factory>();
	
	public Page<Factory> getPage() {
		return page;
	}
	public void setPage(Page<Factory> page) {
		this.page = page;
	}
	public String factory(){
		Page<Factory> findPage = factoryService.findPage("from Factory", page, Factory.class, null);
		page.setUrl("baseinfo_factory");
		super.push(page);
		return "factory";
	}
	/**
	 * 去查看
	 */
	public String toview(){
		List<Factory> find = factoryService.find("from Factory where id=?", Factory.class, new String[]{model.getId()});
		if(UtilFuns.isNotEmpty(find)){
			super.push(find.get(0));
		}
		return "factoryView";
	}
	/**
	 * 去修改
	 */
	public String toupdate(){
		List<Factory> find = factoryService.find("from Factory where id=?", Factory.class, new String[]{model.getId()});
		if(UtilFuns.isNotEmpty(find)){
			super.push(find.get(0));
		}
		return "factoryUpdate";
	}
	/**
	 * 保存
	 */
	public String save(){
		List<Factory> find = factoryService.find("from Factory where id=?", Factory.class, new String[]{model.getId()});
		if(UtilFuns.isNotEmpty(find)){
			Factory factory = find.get(0);
			factory.setAddress(model.getAddress());
			factory.setContacts(model.getContacts());
			factory.setCtype(model.getCtype());
			factory.setFactoryName(model.getFactoryName());
			factory.setFax(model.getFax());
			factory.setFullName(model.getFullName());
			factory.setInspector(model.getInspector());
			factory.setMobile(model.getMobile());
			factory.setPhone(model.getPhone());
			factory.setState(model.getState());
			factoryService.saveOrUpdate(factory);
		}
		return "alist";
	}
	/**
	 * 去添加页面
	 */
	public String toadd(){
		return "toadd";
	}
	/**
	 * 添加
	 */
	public String addFactory(){
		factoryService.saveOrUpdate(model);
		return "alist";
	}
	
	/**
	 * 删除
	 */
	public String deletes(){
		factoryService.deleteById(Factory.class,model.getId());
		return "alist";
	}
	
	private Factory model = new Factory();
	
	@Override
	public Factory getModel() {
		// TODO Auto-generated method stub
		return model;
	}
	
	
}
