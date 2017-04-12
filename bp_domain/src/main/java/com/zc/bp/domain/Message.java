package com.zc.bp.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @Description:	留言板表实体
 */

public class Message implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;	  				
	private String senderId;			//发送人用户ID
	private String message;				//信息内容
	private Double state;				//状态(0未读  1已读  2删除)
	private Date createTime;			//创建时间,根据系统时间生成
	private String recipientId;			//接收人ID
	private String title; 				//标题
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSenderId() {
		return this.senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}	
	
	public String getMessage() {
		return this.message;
	}
	public void setMessage(String message) {
		this.message = message;
	}	
	
	public Double getState() {
		return this.state;
	}
	public void setState(Double state) {
		this.state = state;
	}	
	
	
	public String getRecipientId() {
		return this.recipientId;
	}
	public void setRecipientId(String recipientId) {
		this.recipientId = recipientId;
	}	
}
