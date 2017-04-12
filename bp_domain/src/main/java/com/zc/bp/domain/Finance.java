package com.zc.bp.domain;

import java.util.Date;

public class Finance extends BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;	  	   
	private Date inputDate;     //创建日期
	private String invoiceId;   //发票编号
	private String shiper;      //委托船家
	private String buyer;       //买家
	private String totalprice;  //总价钱
	private String exportId;    //报运单编号
	private String contractIds; //合同编号合集
	private String desc;        //说明
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getInputDate() {
		return inputDate;
	}
	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}
	public String getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}
	public String getShiper() {
		return shiper;
	}
	public void setShiper(String shiper) {
		this.shiper = shiper;
	}
	public String getBuyer() {
		return buyer;
	}
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	public String getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(String totalprice) {
		this.totalprice = totalprice;
	}
	public String getExportId() {
		return exportId;
	}
	public void setExportId(String exportId) {
		this.exportId = exportId;
	}
	public String getContractIds() {
		return contractIds;
	}
	public void setContractIds(String contractIds) {
		this.contractIds = contractIds;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
