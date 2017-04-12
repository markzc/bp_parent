package com.zc.bp.web.cargo;


import java.util.List;

import com.opensymphony.xwork2.ModelDriven;
import com.zc.bp.domain.ContractProduct;
import com.zc.bp.domain.Factory;
import com.zc.bp.exception.SysException;
import com.zc.bp.service.ContractProductService;
import com.zc.bp.service.FactoryService;
import com.zc.bp.utils.Page;
import com.zc.bp.web.BaseAction;

public class ContractProductAction extends BaseAction implements ModelDriven<ContractProduct>{

	private ContractProduct model=new ContractProduct();
	@Override
	public ContractProduct getModel() {
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
	
	private ContractProductService contractProductService;
	public void setContractProductService(ContractProductService contractProductService) {
		this.contractProductService = contractProductService;
	}
	private FactoryService factoryService;
	public void setFactoryService(FactoryService factoryService) {
		this.factoryService = factoryService;
	}
	/**
	 * 分页显示
	 * @return
	 */
	public String list(){
		contractProductService.findPage("from ContractProduct where contract.id=?", page, ContractProduct.class,
				new String[] {model.getContract().getId() });
		
		page.setUrl("contractProductAction_list"); //相对路劲
		super.push(page);
		return "tocreate";
	}
	
	/**
	 * 查看单个
	 */
	public String toview(){
		ContractProduct contractProduct = contractProductService.get(ContractProduct.class, model.getId());
		super.push(contractProduct);
		return "toView";
	}
	
	/**
	 * 转到增加页面
	 */
	public String tocreate(){
		List<Factory> factoryList = factoryService.find("from Factory where ctype='货物' and state=1", Factory.class, null);
		super.put("factoryList", factoryList);
		return list();

	}
	/**
	 * 添加
	 */
	public String insert(){
		// 调用业务方法，实现保存
		contractProductService.saveOrUpdate(model);
		return tocreate();
	}
	/**
	 * 转到修改页面，数据回显
	 */
	public String toupdate(){
		// 1.加载要回显的数据
		ContractProduct obj = contractProductService.get(ContractProduct.class, model.getId());

		// 2.放入值栈中
		super.push(obj);
		
		//3.加载生产厂家列表
		String hql = "from Factory where ctype='货物' and state=1";
		List<Factory> factoryList = factoryService.find(hql, Factory.class, null);

		super.put("factoryList", factoryList);

		// 5.跳页面
		return "toupdate";
	}
	
	/**
	 * 修改
	 */
	public String update(){
		// 1.根据id,加载原有的部门对象
		ContractProduct obj = contractProductService.get(ContractProduct.class, model.getId());

		// 2.设置要修改的属性
		obj.setFactory(model.getFactory());
		obj.setFactoryName(model.getFactoryName());
		obj.setCnumber(model.getCnumber());
		obj.setProductNo(model.getProductNo());
		obj.setProductImage(model.getProductImage());
		obj.setPackingUnit(model.getPackingUnit());
		obj.setLoadingRate(model.getLoadingRate());
		obj.setBoxNum(model.getBoxNum());
		obj.setPrice(model.getPrice());
		obj.setOrderNo(model.getOrderNo());
		obj.setProductDesc(model.getProductDesc());
		obj.setProductRequest(model.getProductRequest());
         
		// 3.保存更新后的结果
		contractProductService.saveOrUpdate(obj);

		// 4.跳页面
		return tocreate();
	}
	
	/**
	 * 删除
	 * @throws SysException 
	 */
	public String delete(){
	
		String id = model.getId();
		contractProductService.deleteById(ContractProduct.class, id);
		return tocreate();
		
	}
	


	

}
