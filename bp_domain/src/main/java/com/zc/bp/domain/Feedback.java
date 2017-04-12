package com.zc.bp.domain;
import java.util.Date;

/**
 * @Description:	FeedbackService接口
 * @Author:			black_Style
 * @Email:		    blackSdbuser@foxmail.com
 * @CreateDate:		2016-12-17 17:22:34
 */

public class Feedback extends BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;	  	
	private String inputBy;			//提出人
	private Date inputTime;			//提出时间
	private String title;			//标题
	private String content;			//内容
	private String classType;		//分类:1管理2安全3建议4其他
	private String tel;				//联系方式
	private String answerBy;		//解决人
	private Date answerTime;		//解决时间
	private String solveMethod;		//解决办法
	private String resolution;		//解决方式:1已修改2无需修改3重复问题4描述不完整5无法再现6其他
	private String difficulty;		//解决难度:1极难2比较难3有难度4一般
	private String isShare;			//是否公开:0不公开1公开
	private String state;			//状态:0未处理1已处理
	
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
	
	public String getDifficulty() {
		return this.difficulty;
	}
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}	
	
	public String getIsShare() {
		return this.isShare;
	}
	public void setIsShare(String isShare) {
		this.isShare = isShare;
	}	
	
	public String getState() {
		return this.state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
