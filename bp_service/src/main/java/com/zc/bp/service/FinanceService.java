package com.zc.bp.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.zc.bp.domain.Finance;
import com.zc.bp.utils.Page;

public interface FinanceService {
	// 查询所有，带条件查询
	public List<Finance> find(String hql, Class<Finance> entityClass, Object[] params);

	// 获取一条记录
	public Finance get(Class<Finance> entityClass, Serializable id);

	// 分页查询，将数据封装到一个page分页工具类对象
	public Page<Finance> findPage(String hql, Page<Finance> page, Class<Finance> entityClass,
			Object[] params);

	// 新增和修改保存
	public void saveOrUpdate(Finance entity);

	// 批量新增和修改保存
	public void saveOrUpdateAll(Collection<Finance> entitys);

	// 单条删除，按id
	public void deleteById(Class<Finance> entityClass, Serializable id);

	// 批量删除
	public void delete(Class<Finance> entityClass, Serializable[] ids);
}
