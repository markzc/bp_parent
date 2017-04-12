package com.zc.bp.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.zc.bp.domain.PackingList;
import com.zc.bp.utils.Page;

public interface PackingListService {


	//查询所有，带条件查询
	public  List<PackingList> find(String hql, Class<PackingList> entityClass, Object[] params);
	//获取一条记录
	public  PackingList get(Class<PackingList> entityClass, Serializable id);
	//分页查询，将数据封装到一个page分页工具类对象
	public  Page<PackingList> findPage(String hql, Page<PackingList> page, Class<PackingList> entityClass, Object[] params);
	
	//新增和修改保存
	public  void saveOrUpdate(PackingList entity);
	//批量新增和修改保存
	public  void saveOrUpdateAll(Collection<PackingList> entitys);
	
	//单条删除，按id
	public void deleteById(Class<PackingList> entityClass, Serializable id);
	//批量删除
	public void delete(Class<PackingList> entityClass, Serializable[] ids);
}
