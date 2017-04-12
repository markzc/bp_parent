package com.zc.bp.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.zc.bp.domain.Feedback;
import com.zc.bp.utils.Page;


/**
 * @Description:	FeedbackService接口
 * @Author:			black_Style
 * @Email:		    blackSdbuser@foxmail.com
 * @CreateDate:		2016-12-17 17:22:34
 */

public interface FeedbackService {

	public List<Feedback> find(String hql, Class<Feedback> entityClass, Object[] params);
	public Feedback get(Class<Feedback> entityClass, Serializable id);
	public Page<Feedback> findPage(String hql, Page<Feedback> page, Class<Feedback> entityClass, Object[] params);
	
	public void saveOrUpdate(Feedback entity);
	public void saveOrUpdateAll(Collection<Feedback> entitys);
	
	public void deleteById(Class<Feedback> entityClass, Serializable id);
	public void delete(Class<Feedback> entityClass, Serializable[] ids);
}
