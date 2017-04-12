package com.zc.bp.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.zc.bp.dao.BaseDao;
import com.zc.bp.domain.Feedback;
import com.zc.bp.service.FeedbackService;
import com.zc.bp.utils.Page;


/**
 * @Description:	FeedbackService接口
 * @Author:			black_Style
 * @Email:		    blackSdbuser@foxmail.com
 * @CreateDate:		2016-12-17 17:22:34
 */

public class FeedbackServiceImpl implements FeedbackService {
	//spring注入dao
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<Feedback> find(String hql, Class<Feedback> entityClass, Object[] params) {
		return baseDao.find(hql, Feedback.class, params);
	}

	public Feedback get(Class<Feedback> entityClass, Serializable id) {
		return baseDao.get(Feedback.class, id);
	}

	public Page<Feedback> findPage(String hql, Page<Feedback> page, Class<Feedback> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, entityClass, params);
	}

	public void saveOrUpdate(Feedback entity) {
		if(entity.getId()==null){								//代表新增
			entity.setState("0");
		}
		baseDao.saveOrUpdate(entity);
	}



	public void saveOrUpdateAll(Collection<Feedback> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}

	public void deleteById(Class<Feedback> entityClass, Serializable id) {
		baseDao.deleteById(Feedback.class, id);
	}

	public void delete(Class<Feedback> entityClass, Serializable[] ids) {
		baseDao.delete(Feedback.class, ids);
	}

}
