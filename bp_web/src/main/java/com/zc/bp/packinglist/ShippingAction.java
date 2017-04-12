package com.zc.bp.packinglist;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;
import com.zc.bp.domain.PackingList;
import com.zc.bp.domain.ShippingOrder;
import com.zc.bp.service.ShippingService;
import com.zc.bp.utils.Page;
import com.zc.bp.web.BaseAction;
import com.zc.bp.web.cargo.DuCheng;
/**
 * 委托管理
 * @author zc
 *
 */
public class ShippingAction extends BaseAction implements ModelDriven<ShippingOrder>{

	private ShippingService shippingService;
	
	
	public ShippingService getShippingService() {
		return shippingService;
	}
	public void setShippingService(ShippingService shippingService) {
		this.shippingService = shippingService;
	}

	/**
	 * 查看
	 */
	public String toview(){
		ShippingOrder shippingOrder = shippingService.get(ShippingOrder.class, model.getId());
		super.push(shippingOrder);
		return "toview";
	}
	
	/**
	 * 打印
	 * @throws Exception 
	 */
	public String printShip() throws Exception{
		ShippingOrder shippingOrder = shippingService.get(ShippingOrder.class, model.getId());
		String path = ServletActionContext.getServletContext().getRealPath("/");
		DuCheng duCheng = new DuCheng();
		duCheng.printShip(shippingOrder,path, ServletActionContext.getResponse());
		return NONE;
	}
	
	/**
	 * 删除
	 */
	public String delete(){
		String[] split = model.getId().split(",");
		shippingService.delete(ShippingOrder.class,split);
		return "update";
	}
	
	/**
	 * 修改
	 */
	public String update(){
		shippingService.saveOrUpdate(model);
		return "update";
	}
	/**
	 * 去修改
	 */
	public String toupdate(){
		ShippingOrder shippingOrder = shippingService.get(ShippingOrder.class, model.getId());
		super.push(shippingOrder);
		return "toupdate";
	}
	
	/**
	 * 委托
	 * @return
	 */
	public String insert(){
		model.setState(0);
		shippingService.saveOrUpdate(model);
		return "insert";
	}
	private Page<ShippingOrder> page = new Page<>();
	/**
	 * 分页查找委托单
	 */
	public String list(){
		shippingService.findPage("from ShippingOrder", page, ShippingOrder.class, null);
		page.setUrl("shippingOrderAction_list"); // 相对路劲
		super.push(page);
		
		return "list";
	}

	private ShippingOrder model = new ShippingOrder();
	@Override
	public ShippingOrder getModel() {
		return model;
	}
}
