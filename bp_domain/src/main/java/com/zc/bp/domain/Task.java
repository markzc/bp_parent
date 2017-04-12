package com.zc.bp.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Administrator
 *
 */
public class Task extends BaseEntity implements Serializable{
	
	private String id;
	/**
	 * id 
	userId  接收任务的人 user
	missionType  任务类别 0 购销合同 1 module
	state 任务状态 0 未完成 1 已完成 2 转交
	description 描述
	finishDate 任务提交时间
	createBy 谁创建的
	createTime  创建的时间
	updateBy 谁修改的
	updateTime 修改的时间

	 */
	private User user;//接收任务的人
	private Module module;//任务种类
	
	private Integer state;//任务状态 0 未完成 1 已完成 2 转交
	private String description;//描述
	private Date finishDate;
	private Integer defaultState;
	
	
	public Integer getDefaultState() {
		return defaultState;
	}
	public void setDefaultState(Integer defaultState) {
		this.defaultState = defaultState;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Module getModule() {
		return module;
	}
	public void setModule(Module module) {
		this.module = module;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	
	
}
