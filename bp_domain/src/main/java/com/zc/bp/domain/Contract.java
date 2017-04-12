package com.zc.bp.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

public class Contract extends BaseEntity implements Serializable{
	
	private String id;
	private String offeror;
	private String contractNo;
	private Date signingDate;
	private String inputBy;
	private String checkBy;
	private String inspector;
	private Double totalAmount;
	private String crequest;
	private String customName;
	private Date shipTime ;
	private Integer importNum;
	private Date deliveryPeriod;
	private Integer oldState;
	private Integer outState;
	private String tradeTerms;
	private String printStyle;
	private String remark;
	private Integer state;
	
	private Set<ContractProduct> contractProducts;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOfferor() {
		return offeror;
	}

	public void setOfferor(String offeror) {
		this.offeror = offeror;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public Date getSigningDate() {
		return signingDate;
	}

	public void setSigningDate(Date signingDate) {
		this.signingDate = signingDate;
	}

	public String getInputBy() {
		return inputBy;
	}

	public void setInputBy(String inputBy) {
		this.inputBy = inputBy;
	}

	public String getCheckBy() {
		return checkBy;
	}

	public void setCheckBy(String checkBy) {
		this.checkBy = checkBy;
	}

	public String getInspector() {
		return inspector;
	}

	public void setInspector(String inspector) {
		this.inspector = inspector;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getCrequest() {
		return crequest;
	}

	public void setCrequest(String crequest) {
		this.crequest = crequest;
	}

	public String getCustomName() {
		return customName;
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}

	public Date getShipTime() {
		return shipTime;
	}

	public void setShipTime(Date shipTime) {
		this.shipTime = shipTime;
	}

	

	public Integer getImportNum() {
		return importNum;
	}

	public void setImportNum(Integer importNum) {
		this.importNum = importNum;
	}

	public Date getDeliveryPeriod() {
		return deliveryPeriod;
	}

	public void setDeliveryPeriod(Date deliveryPeriod) {
		this.deliveryPeriod = deliveryPeriod;
	}

	public Integer getOldState() {
		return oldState;
	}

	public void setOldState(Integer oldState) {
		this.oldState = oldState;
	}

	public Integer getOutState() {
		return outState;
	}

	public void setOutState(Integer outState) {
		this.outState = outState;
	}

	public String getTradeTerms() {
		return tradeTerms;
	}

	public void setTradeTerms(String tradeTerms) {
		this.tradeTerms = tradeTerms;
	}

	public String getPrintStyle() {
		return printStyle;
	}

	public void setPrintStyle(String printStyle) {
		this.printStyle = printStyle;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Set<ContractProduct> getContractProducts() {
		return contractProducts;
	}

	public void setContractProducts(Set<ContractProduct> contractProducts) {
		this.contractProducts = contractProducts;
	}
	

}
