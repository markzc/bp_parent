package com.zc.bp.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.zc.bp.domain.ShippingOrder;
import com.zc.bp.utils.Page;

public interface ShippingService {

	// 查询所有，带条件查询
	public List<ShippingOrder> find(String hql, Class<ShippingOrder> entityClass, Object[] params);

	// 获取一条记录
	public ShippingOrder get(Class<ShippingOrder> entityClass, Serializable id);

	// 分页查询，将数据封装到一个page分页工具类对象
	public Page<ShippingOrder> findPage(String hql, Page<ShippingOrder> page, Class<ShippingOrder> entityClass,
			Object[] params);

	// 新增和修改保存
	public void saveOrUpdate(ShippingOrder entity);

	// 批量新增和修改保存
	public void saveOrUpdateAll(Collection<ShippingOrder> entitys);

	// 单条删除，按id
	public void deleteById(Class<ShippingOrder> entityClass, Serializable id);

	// 批量删除
	public void delete(Class<ShippingOrder> entityClass, Serializable[] ids);
}
