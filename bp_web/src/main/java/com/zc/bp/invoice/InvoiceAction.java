package com.zc.bp.invoice;

import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ModelDriven;
import com.sun.mail.iap.Response;
import com.zc.bp.domain.Export;
import com.zc.bp.domain.Finance;
import com.zc.bp.domain.Invoice;
import com.zc.bp.domain.PackingList;
import com.zc.bp.domain.ShippingOrder;
import com.zc.bp.domain.User;
import com.zc.bp.service.ExportService;
import com.zc.bp.service.FinanceService;
import com.zc.bp.service.InvoiceService;
import com.zc.bp.service.PackingListService;
import com.zc.bp.service.ShippingService;
import com.zc.bp.utils.Page;
import com.zc.bp.utils.SysConstant;
import com.zc.bp.utils.UtilFuns;
import com.zc.bp.web.BaseAction;
import com.zc.bp.web.cargo.DuCheng;
/**
 * 发票
 * @author zc
 *
 */
public class InvoiceAction extends BaseAction implements ModelDriven<Invoice> {

	private Invoice model = new Invoice();
	private FinanceService financeService;
	public void setFinanceService(FinanceService financeService) {
		this.financeService = financeService;
	}

	@Override
	public Invoice getModel() {
		return model;
	}

	private Page<Invoice> page = new Page<>();

	public Page<Invoice> getPage() {
		return page;
	}

	public void setPage(Page<Invoice> page) {
		this.page = page;
	}

	public void setModel(Invoice model) {
		this.model = model;
	}

	private InvoiceService invoiceService;

	public void setInvoiceService(InvoiceService invoiceService) {
		this.invoiceService = invoiceService;
	}
	
	/**
	 * 打印发票
	 * @return
	 * @throws Exception 
	 */
	public String printInvoice() throws Exception{
		Invoice invoice = invoiceService.get(Invoice.class, model.getId());
		String path = ServletActionContext.getServletContext().getRealPath("/");
		DuCheng duCheng = new DuCheng();
		duCheng.printInvoice(invoice,path, ServletActionContext.getResponse());
		return NONE;
	}

	/**
	 * 分页查询
	 * 
	 * @return
	 */
	public String list() {
		invoiceService.findPage("from Invoice", page, Invoice.class, null);
		page.setUrl("invoiceAction_list"); // 相对路劲
		super.push(page);
		return "list";
	}

	/**
	 * 查看
	 */
	public String toview() {
		Invoice invoice = invoiceService.get(Invoice.class, model.getId());
		super.push(invoice);
		return "toview";
	}

	/**
	 * 新增
	 */
	public String tocreate() {
		return "tocreate";
	}

	/**
	 * 保存
	 */
	public String insert() {
		invoiceService.saveOrUpdate(model);
		return "alist";
	}

	/**
	 * 去修改
	 * 
	 * @return
	 */
	public String toupdate() {
		Invoice invoice = invoiceService.get(Invoice.class, model.getId());
		super.push(invoice);
		return "toupdate";
	}

	/**
	 * 修改
	 */
	public String update() {
		Invoice invoice = invoiceService.get(Invoice.class, model.getId());
		invoice.setBlNo(model.getBlNo());
		invoice.setBuyer(model.getBuyer());
		invoice.setPrice(model.getPrice());
		invoice.setSaller(model.getSaller());
		invoice.setScNo(model.getScNo());
		invoice.setTradeTerms(model.getTradeTerms());
		invoiceService.saveOrUpdate(invoice);
		return "alist";
	}

	/**
	 * 删除
	 */
	public String delete() {
		String[] id = model.getId().split(",");
		invoiceService.delete(Invoice.class, id);
		return "alist";
	}
	
	private ExportService exportService;
	private PackingListService packingListService;
	private ShippingService shippingService;

	public ShippingService getShippingService() {
		return shippingService;
	}

	public void setShippingService(ShippingService shippingService) {
		this.shippingService = shippingService;
	}

	public PackingListService getPackingListService() {
		return packingListService;
	}

	public void setPackingListService(PackingListService packingListService) {
		this.packingListService = packingListService;
	}

	public ExportService getExportService() {
		return exportService;
	}

	public void setExportService(ExportService exportService) {
		this.exportService = exportService;
	}

	/**
	 * 提交
	 */
	public String subState() {
		Invoice invoice = invoiceService.get(Invoice.class, model.getId());
		
		changeState(1);
		//生成订单
		Finance finance = new Finance();
		finance.setInputDate(new Date());
		finance.setBuyer(invoice.getBuyer());
		finance.setExportId(invoice.getScNo());
		finance.setTotalprice(invoice.getPrice());
		
		Export export = exportService.get(Export.class,invoice.getScNo());
		finance.setContractIds(export.getContractIds());

		finance.setInvoiceId(invoice.getId());
		List<PackingList> packingLists = packingListService.find("from PackingList where invoiceNo=?", PackingList.class, new String[]{invoice.getId()});
		ShippingOrder shippingOrder=null;
		if(UtilFuns.isNotEmpty(packingLists)){
			shippingOrder = shippingService.get(ShippingOrder.class,packingLists.get(0).getId());
		}
		if(shippingOrder!=null){
			finance.setShiper(shippingOrder.getShipper());
		}
		
		financeService.saveOrUpdate(finance);
		return "alist";
	}
	/**
	 * 取消
	 */
	public String lowState() {
		changeState(0);
		financeService.deleteById(Finance.class, model.getId());
		return "alist";
	}

	// 修改状态
	private void changeState(int state) {
		// 1.得到用户所选中的多个购销合同的id
		String ids[] = model.getId().split(", ");

		// 2.遍历ids数组，得到每个购销合同对象
		for (String id : ids) {
			// 根据购销合同的id,加载购销合同对象
			Invoice invoice = invoiceService.get(Invoice.class, id);

			// 修改状态
			invoice.setState(state);

			// 保存更新的结果信息
			invoiceService.saveOrUpdate(invoice);
		}
	}

}
