package com.zc.bp.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.zc.bp.domain.ShoppingList;
import com.zc.bp.utils.Page;

public interface ShoppingListService {

	//查询所有，带条件查询
	public  List<ShoppingList> find(String hql, Class<ShoppingList> entityClass, Object[] params);
	//获取一条记录
	public  ShoppingList get(Class<ShoppingList> entityClass, Serializable id);
	//分页查询，将数据封装到一个page分页工具类对象
	public  Page<ShoppingList> findPage(String hql, Page<ShoppingList> page, Class<ShoppingList> entityClass, Object[] params);
	
	//新增和修改保存
	public  void saveOrUpdate(ShoppingList entity);
	//批量新增和修改保存
	public  void saveOrUpdateAll(Collection<ShoppingList> entitys);
	
	//单条删除，按id
	public void deleteById(Class<ShoppingList> entityClass, Serializable id);
	//批量删除
	public void delete(Class<ShoppingList> entityClass, Serializable[] ids);
}
