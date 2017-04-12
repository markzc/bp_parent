package com.zc.bp.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
/**
 * create table DEPT_P  (
   DEPT_ID              varchar2(40)   not null,
   DEPT_NAME            varchar2(50),
   PARENT_ID            varchar2(40),
   STATE                NUMBER(6,0),
   constraint PK_DEPT_P primary key (DEPT_ID)
);
 * @author zc
 *
 */
public class Dept implements Serializable {

	private String id;
	private String deptName; //部门名称
	private Dept parent;     //自关联，多个子部门对应一个父部门。
	private Integer state;
	private Set<User> users= new HashSet<>(0);
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public Dept getParent() {
		return parent;
	}
	public void setParent(Dept parent) {
		this.parent = parent;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	
	
}
