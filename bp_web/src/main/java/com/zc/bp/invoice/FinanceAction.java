package com.zc.bp.invoice;

import com.opensymphony.xwork2.ModelDriven;
import com.sun.mail.iap.Response;
import com.zc.bp.domain.Finance;
import com.zc.bp.domain.Finance;
import com.zc.bp.domain.PackingList;
import com.zc.bp.service.FinanceService;
import com.zc.bp.service.InvoiceService;
import com.zc.bp.utils.Page;
import com.zc.bp.utils.SysConstant;
import com.zc.bp.web.BaseAction;

public class FinanceAction extends BaseAction implements ModelDriven<Finance> {

	private Finance model = new Finance();
	private FinanceService financeService;
	public void setFinanceService(FinanceService financeService) {
		this.financeService = financeService;
	}

	@Override
	public Finance getModel() {
		return model;
	}

	private Page<Finance> page = new Page<>();

	public Page<Finance> getPage() {
		return page;
	}

	public void setPage(Page<Finance> page) {
		this.page = page;
	}

	public void setModel(Finance model) {
		this.model = model;
	}

	private InvoiceService invoiceService;

	public void setInvoiceService(InvoiceService invoiceService) {
		this.invoiceService = invoiceService;
	}

	/**
	 * 分页查询
	 * 
	 * @return
	 */
	public String list() {
		financeService.findPage("from Finance", page, Finance.class, null);
		page.setUrl("invoiceAction_list"); // 相对路劲
		super.push(page);
		return "list";
	}

	/**
	 * 查看
	 */
	public String toview() {
		Finance invoice = financeService.get(Finance.class, model.getId());
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
		financeService.saveOrUpdate(model);
		return "alist";
	}

	/**
	 * 去修改
	 * 
	 * @return
	 */
	public String toupdate() {
		Finance invoice = financeService.get(Finance.class, model.getId());
		super.push(invoice);
		return "toupdate";
	}

	/**
	 * 修改
	 */
	public String update() {
		financeService.saveOrUpdate(model);
		return "alist";
	}

	/**
	 * 删除
	 */
	public String delete() {
		String[] id = model.getId().split(",");
		financeService.delete(Finance.class, id);
		return "alist";
	}

	

	

}
