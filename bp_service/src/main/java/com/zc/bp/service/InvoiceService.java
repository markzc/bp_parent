package com.zc.bp.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.zc.bp.domain.Invoice;
import com.zc.bp.utils.Page;

public interface InvoiceService {
	// 查询所有，带条件查询
	public List<Invoice> find(String hql, Class<Invoice> entityClass, Object[] params);

	// 获取一条记录
	public Invoice get(Class<Invoice> entityClass, Serializable id);

	// 分页查询，将数据封装到一个page分页工具类对象
	public Page<Invoice> findPage(String hql, Page<Invoice> page, Class<Invoice> entityClass,
			Object[] params);

	// 新增和修改保存
	public void saveOrUpdate(Invoice entity);

	// 批量新增和修改保存
	public void saveOrUpdateAll(Collection<Invoice> entitys);

	// 单条删除，按id
	public void deleteById(Class<Invoice> entityClass, Serializable id);

	// 批量删除
	public void delete(Class<Invoice> entityClass, Serializable[] ids);
}
