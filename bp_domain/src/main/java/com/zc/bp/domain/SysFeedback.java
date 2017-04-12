package com.zc.bp.domain;

import java.util.Date;

/**
 * @Description:	SysFeedbackService接口
 * @Author:			zc
 * @Company:		http://java.itcast.cn
 * @CreateDate:		2016-12-17 20:20:41
 */

public class SysFeedback extends BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;	  	
	private String inputBy;			
	private Date inputTime;			
	private String title;			
	private String content;			
	private String classType;			
	private String tel;			
	private String answerBy;			
	private Date answerTime;			
	private String solveMethod;			
	private String resolution;			
	private Integer difficulty;			
	private String isShare;			
	private Integer state;	
	private String createBy;
	private String createDept;
	private Date createTime;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getInputBy() {
		return this.inputBy;
	}
	public void setInputBy(String inputBy) {
		this.inputBy = inputBy;
	}	
	
	public Date getInputTime() {
		return this.inputTime;
	}
	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}	
	
	public String getTitle() {
		return this.title;
	}
	public void setTitle(String title) {
		this.title = title;
	}	
	
	public String getContent() {
		return this.content;
	}
	public void setContent(String content) {
		this.content = content;
	}	
	
	public String getClassType() {
		return this.classType;
	}
	public void setClassType(String classType) {
		this.classType = classType;
	}	
	
	public String getTel() {
		return this.tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}	
	
	public String getAnswerBy() {
		return this.answerBy;
	}
	public void setAnswerBy(String answerBy) {
		this.answerBy = answerBy;
	}	
	
	public Date getAnswerTime() {
		return this.answerTime;
	}
	public void setAnswerTime(Date answerTime) {
		this.answerTime = answerTime;
	}	
	
	public String getSolveMethod() {
		return this.solveMethod;
	}
	public void setSolveMethod(String solveMethod) {
		this.solveMethod = solveMethod;
	}	
	
	public String getResolution() {
		return this.resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}	
	
	public Integer getDifficulty() {
		return this.difficulty;
	}
	public void setDifficulty(Integer difficulty) {
		this.difficulty = difficulty;
	}	
	
	public String getIsShare() {
		return this.isShare;
	}
	public void setIsShare(String isShare) {
		this.isShare = isShare;
	}	
	
	public Integer getState() {
		return this.state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getCreateDept() {
		return createDept;
	}
	public void setCreateDept(String createDept) {
		this.createDept = createDept;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
