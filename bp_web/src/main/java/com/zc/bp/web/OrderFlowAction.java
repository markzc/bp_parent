package com.zc.bp.web;

import org.hibernate.annotations.common.util.StringHelper;

import com.opensymphony.xwork2.ModelDriven;
import com.zc.bp.domain.ShoppingList;
import com.zc.bp.domain.User;
import com.zc.bp.service.ShoppingListService;
import com.zc.bp.utils.Page;
import com.zc.bp.utils.SysConstant;

public class OrderFlowAction extends BaseAction implements ModelDriven<ShoppingList>{

	private ShoppingList model = new ShoppingList();
	private ShoppingListService shoppingListService;
	public ShoppingListService getShoppingListService() {
		return shoppingListService;
	}
	public void setShoppingListService(ShoppingListService shoppingListService) {
		this.shoppingListService = shoppingListService;
	}
	
	private Page page = new Page();
	
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	@Override
	public ShoppingList getModel() {
		return model;
	}
	/**
	 * 分页查询采购单
	 * @return
	 */
	public String orderTaskList(){
		shoppingListService.findPage("from ShoppingList", page, ShoppingList.class, null);
		page.setUrl("orderFlowAction_orderTaskList");
		super.push(page);
		return "list";
	}
	/**
	 * 添加采购单
	 */
	public String addOrder(){
		
		return "toAddOrder";
	}

	public String insert(){
		User user = (User)session.get(SysConstant.CURRENT_USER_INFO);
		model.setUname(user.getUsername());
		shoppingListService.saveOrUpdate(model);
		return "alist";
	}
	/**
	 * 去修改
	 */
	public String toupdate(){
		ShoppingList shoppingList = shoppingListService.get(ShoppingList.class, model.getSid());
		super.push(shoppingList);
		return "toupdate";
	}
	/**
	 * 修改
	 */
	public String update(){
		shoppingListService.saveOrUpdate(model);
		return "alist";
	}
	
	/**
	 * 删除
	 */
	public String delete(){
		String[] ids = model.getSid().split(", ");
		if(ids.length!=0 && ids!=null){
			shoppingListService.delete(ShoppingList.class, ids);
		}
		return "alist";
	}
	
	/**
	 * 部署流程
	 */
	public String deploy(){
		return "deploy";
	}
}
