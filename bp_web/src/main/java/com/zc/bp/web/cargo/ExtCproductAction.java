package com.zc.bp.web.cargo;

import java.util.List;


import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;
import com.zc.bp.domain.ExtCproduct;
import com.zc.bp.domain.Factory;
import com.zc.bp.service.ExtCproductService;
import com.zc.bp.service.FactoryService;
import com.zc.bp.utils.Page;
import com.zc.bp.web.BaseAction;

public class ExtCproductAction extends BaseAction implements ModelDriven<ExtCproduct> {
	private ExtCproduct model = new ExtCproduct();

	@Override
	public ExtCproduct getModel() {
		return model;
	}

	private FactoryService factoryService;

	public void setFactoryService(FactoryService factoryService) {
		this.factoryService = factoryService;
	}

	private ExtCproductService extCproductService;

	public void setExtCproductService(ExtCproductService extCproductService) {
		this.extCproductService = extCproductService;
	}

	// 分组插件
	private Page page = new Page();

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	/**
	 * 转到添加页面
	 * 
	 * @return
	 */
	public String tocreate() {

		List<Factory> factoryList = factoryService.find("from Factory where ctype='附件' and state=1", Factory.class,
				null);
		super.put("factoryList", factoryList);
		return list();
	}

	/**
	 * 分页显示
	 * 
	 * @return
	 */
	public String list() {

		//解决添加跟修改后的数据回显问题
		String id = "";
		if (model == null || model.getContractProduct() == null) {
			id = (String) ServletActionContext.getRequest().getAttribute("id");
			extCproductService.findPage("from ExtCproduct where contractProduct.id=?", page, ExtCproduct.class,
					new String[] { id });
		} else {

			extCproductService.findPage("from ExtCproduct where contractProduct.id=?", page, ExtCproduct.class,
					new String[] { model.getContractProduct().getId() });
		}
		page.setUrl("extCproductAction_list"); // 相对路劲
		super.push(page);
		return "tocreate";
	}

	/**
	 * 转到修改
	 */
	public String toupdate() {

		// 1.加载要回显的数据
		ExtCproduct obj = extCproductService.get(ExtCproduct.class, model.getId());

		// 2.放入值栈中
		super.push(obj);

		// 3.显示下拉列表
		List<Factory> factoryList = factoryService.find("from Factory where ctype='附件' and state=1", Factory.class,
				null);
		super.put("factoryList", factoryList);
		return "toupdate";
	}

	/**
	 * 修改
	 */
	public String update() {
		ExtCproduct obj = extCproductService.get(ExtCproduct.class, model.getId());
		obj.setFactory(model.getFactory());
		obj.setFactoryName(model.getFactoryName());
		obj.setProductNo(model.getProductNo());
		obj.setProductImage(model.getProductImage());
		obj.setCnumber(model.getCnumber());
		obj.setPackingUnit(model.getPackingUnit());
		obj.setOrderNo(model.getOrderNo());
		obj.setPrice(model.getPrice());
		obj.setProductDesc(model.getProductDesc());
		obj.setProductRequest(model.getProductRequest());

		extCproductService.saveOrUpdate(obj);

		return "alist";
	}

	/**
	 * 增加
	 */
	public String insert() {

		extCproductService.saveOrUpdate(model);

		return "alist";
	}

	/**
	 * 删除
	 */
	public String delete() {
		extCproductService.deleteById(ExtCproduct.class, model);
		return tocreate();
	}
}
