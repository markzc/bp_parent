package com.zc.bp.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.zc.bp.domain.SysFeedback;
import com.zc.bp.utils.Page;


/**
 * @Description:	SysFeedbackService接口
 * @Company:		http://java.itcast.cn
 * @CreateDate:		2016-12-17 20:20:41
 */

public interface SysFeedbackService {

	public List<SysFeedback> find(String hql, Class<SysFeedback> entityClass, Object[] params);
	public SysFeedback get(Class<SysFeedback> entityClass, Serializable id);
	public Page<SysFeedback> findPage(String hql, Page<SysFeedback> page, Class<SysFeedback> entityClass, Object[] params);
	
	public void save(SysFeedback entity);
	public void saveOrUpdate(SysFeedback entity);
	public void saveOrUpdateAll(Collection<SysFeedback> entitys);
	
	public void deleteById(Class<SysFeedback> entityClass, Serializable id);
	public void delete(Class<SysFeedback> entityClass, Serializable[] ids);
}
