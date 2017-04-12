package com.zc.bp.packinglist;

import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.sql.Delete;
import org.hibernate.sql.Update;

import com.opensymphony.xwork2.ModelDriven;
import com.zc.bp.domain.Export;
import com.zc.bp.domain.Invoice;
import com.zc.bp.domain.PackingList;
import com.zc.bp.domain.ShippingOrder;
import com.zc.bp.service.ExportService;
import com.zc.bp.service.InvoiceService;
import com.zc.bp.service.PackingListService;
import com.zc.bp.utils.Page;
import com.zc.bp.web.BaseAction;
import com.zc.bp.web.cargo.DuCheng;
/**
 * 装箱管理
 * @author zc
 *
 */
public class PackingListAction extends BaseAction implements ModelDriven<PackingList> {

	private PackingList model = new PackingList();
	private PackingListService packingListService;
	private InvoiceService invoiceService;

	public void setPackingListService(PackingListService packingListService) {
		this.packingListService = packingListService;
	}
	
	public void setInvoiceService(InvoiceService invoiceService) {
		this.invoiceService = invoiceService;
	}



	private Page<PackingList> page = new Page<>();

	public Page<PackingList> getPage() {
		return page;
	}

	public void setPage(Page<PackingList> page) {
		this.page = page;
	}

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	public String list() {
		packingListService.findPage("from PackingList", page, PackingList.class, null);
		page.setUrl("packingListAction_list"); // 相对路劲
		super.push(page);
		return "list";
	}
	
	/**
	 * 打印装箱单
	 * @return
	 * @throws Exception 
	 */
	public String printPacking() throws Exception{
		PackingList packingList = packingListService.get(PackingList.class, model.getId());
		String path = ServletActionContext.getServletContext().getRealPath("/");
		DuCheng duCheng = new DuCheng();
		duCheng.printPacking(packingList,path, ServletActionContext.getResponse());
		return NONE;
	}

	/**
	 * 查看单个
	 */
	public String toview() {

		PackingList packing = packingListService.get(PackingList.class, model.getId());
		super.push(packing);
		return "toview";
	}

	/**
	 * 到增加页面
	 */
	public String tocreate() {
		return "tocreate";
	}

	/**
	 * 到修改页面
	 */
	public String toupdate() {
		PackingList packing = packingListService.get(PackingList.class, model.getId());
		super.push(packing);
		return "toupdate";
	}

	/**
	 * 修改
	 */
	public String update() {
		PackingList packing = packingListService.get(PackingList.class, model.getId());
		packing.setBuyer(model.getBuyer());
		packing.setSeller(model.getSeller());
		packing.setInvoiceNo(model.getInvoiceNo());
		packing.setInvoiceDate(model.getInvoiceDate());
		packing.setMarks(model.getMarks());
		packing.setDescriptions(model.getDescriptions());
		packingListService.saveOrUpdate(packing);
		return "alist";
	}

	/**
	 * 删除
	 */
	public String Delete() {
		String[] idStrings = model.getId().split(", ");
		packingListService.delete(PackingList.class, idStrings);
		return "alist";
	}
	
	private ExportService exportService;
	public ExportService getExportService() {
		return exportService;
	}

	public void setExportService(ExportService exportService) {
		this.exportService = exportService;
	}

	/**
	 * 委托
	 * ordertype海运/空运
	 * 提单抬头
	 * 转船港
	 * 效期
	 * 分期
	 * 转船
	 * 分数
	 * 运输要求
	 * 运费说明
	 * 复核人
	 * 
	 */
	public String weituo() {
		changeState(1);
		PackingList packingList = packingListService.get(PackingList.class, model.getId());
		//生成委托单
		ShippingOrder shippingOrder = new ShippingOrder();
		shippingOrder.setId(packingList.getId());
		shippingOrder.setLcNo(packingList.getInvoiceNo());
		shippingOrder.setNotifyParty(packingList.getSeller());
		
		Export export = exportService.get(Export.class, packingList.getExportIds());
		shippingOrder.setPortOfLoading(export.getShipmentPort());
		shippingOrder.setPortOfDischarge(export.getDestinationPort());
		shippingOrder.setFreight(export.getPriceCondition()); //价钱
		
		shippingOrder.setRemark(packingList.getDescriptions());
		
		//生成发票
		Invoice invoice = new Invoice();
		invoice.setId(packingList.getInvoiceNo());
		invoice.setScNo(packingList.getExportIds()); //报运单编号合集
		invoice.setTradeTerms(packingList.getId());  //委托编号
		invoice.setBlNo(packingList.getExportNos());  //信用证号
		invoice.setPrice(export.getPriceCondition());
		invoice.setSaller(packingList.getSeller());
		invoice.setCreateTime(new Date());
		invoice.setBuyer(packingList.getBuyer());
		invoice.setState(0);
		invoiceService.saveOrUpdate(invoice);
		
		super.push(shippingOrder);
		return "weituo";
	}

	@Override
	public PackingList getModel() {
		return model;
	}
	
	

	// 修改状态 
	private void changeState(int state) {
		// 1.得到用户所选中的多个购销合同的id
		String ids[] = model.getId().split(", ");

		// 2.遍历ids数组，得到每个购销合同对象
		for (String id : ids) {
			// 根据购销合同的id,加载购销合同对象
			PackingList packingList = packingListService.get(PackingList.class, id);

			// 修改状态
			packingList.setState(state);

			// 保存更新的结果信息
			packingListService.saveOrUpdate(packingList);
		}
	}

}
