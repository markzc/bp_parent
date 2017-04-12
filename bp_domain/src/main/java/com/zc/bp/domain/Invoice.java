package com.zc.bp.domain;

import java.util.Date;

public class Invoice extends BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;	  	        //id
	private String scNo;			//packing.getExportNos S/C就是报运的合同号
	private String blNo;			//信用证号
	private String tradeTerms;		//委托编号
	private Integer state;			//状态
	private String price;           //价钱
	private String buyer;           //买家
	private String saller;          //卖家
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getScNo() {
		return scNo;
	}
	public void setScNo(String scNo) {
		this.scNo = scNo;
	}
	public String getBlNo() {
		return blNo;
	}
	public void setBlNo(String blNo) {
		this.blNo = blNo;
	}
	public String getTradeTerms() {
		return tradeTerms;
	}
	public void setTradeTerms(String tradeTerms) {
		this.tradeTerms = tradeTerms;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getBuyer() {
		return buyer;
	}
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	public String getSaller() {
		return saller;
	}
	public void setSaller(String saller) {
		this.saller = saller;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}

