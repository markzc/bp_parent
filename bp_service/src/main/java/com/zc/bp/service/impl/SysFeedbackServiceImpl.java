package com.zc.bp.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.zc.bp.dao.BaseDao;
import com.zc.bp.domain.SysFeedback;
import com.zc.bp.service.SysFeedbackService;
import com.zc.bp.utils.Page;
import com.zc.bp.utils.UtilFuns;

/**
 * @Description:	SysFeedbackService接口
 * @Company:		http://java.itcast.cn
 * @CreateDate:		2016-12-17 20:20:41
 */

public class SysFeedbackServiceImpl implements SysFeedbackService {
	//spring注入dao
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<SysFeedback> find(String hql, Class<SysFeedback> entityClass, Object[] params) {
		return baseDao.find(hql, SysFeedback.class, params);
	}

	public SysFeedback get(Class<SysFeedback> entityClass, Serializable id) {
		return baseDao.get(SysFeedback.class, id);
	}

	public Page<SysFeedback> findPage(String hql, Page<SysFeedback> page, Class<SysFeedback> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, SysFeedback.class, params);
	}

	public void saveOrUpdate(SysFeedback entity) {
		if(UtilFuns.isEmpty(entity.getId())){								//代表新增
			entity.setState(0);//状态：0未解决1已解决
			entity.setAnswerBy(UtilFuns.convertNull(entity.getAnswerBy()));		
		}
		baseDao.saveOrUpdate(entity);
	}



	public void saveOrUpdateAll(Collection<SysFeedback> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}

	public void deleteById(Class<SysFeedback> entityClass, Serializable id) {
		baseDao.deleteById(SysFeedback.class, id);
	}

	public void delete(Class<SysFeedback> entityClass, Serializable[] ids) {
		baseDao.delete(SysFeedback.class, ids);
	}
	
	@Override
	public void save(SysFeedback entity) {
		// TODO Auto-generated method stub
		
	} 

}

