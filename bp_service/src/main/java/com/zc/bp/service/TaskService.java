package com.zc.bp.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.zc.bp.domain.Task;
import com.zc.bp.domain.User;
import com.zc.bp.utils.Page;





public interface TaskService {


	//查询所有，带条件查询
	public List<Task> find(String hql, Class<Task> entityClass, Object[] params);
	//获取一条记录
	public  Task get(Class<Task> entityClass, Serializable id);
	//分页查询，将数据封装到一个page分页工具类对象
	public  Page<Task> findPage(String hql, Page<Task> page, Class<Task> entityClass, Object[] params);
	
	//新增和修改保存
	public  void saveOrUpdate(Task entity) ;
	//批量新增和修改保存
	public  void saveOrUpdateAll(Collection<Task> entitys);
	
	//单条删除，按id
	public  void deleteById(Class<Task> entityClass, Serializable id);
	//批量删除
	public  void delete(Class<Task> entityClass, Serializable[] ids);
	public List<User> findUser(Task model, String moduleId, User user);
/*//	修改任务信息
	public void updateTask(Task obj, String userId);*/
	
}
