package com.zc.bp.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import com.zc.bp.dao.BaseDao;
import com.zc.bp.domain.Factory;
import com.zc.bp.service.FactoryService;
import com.zc.bp.utils.Page;
import com.zc.bp.utils.UtilFuns;

public class FactoryServiceImpl implements FactoryService {
	
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<Factory> find(String hql, Class<Factory> entityClass, Object[] params) {
		
		return baseDao.find(hql, entityClass, params);
	}

	@Override
	public Factory get(Class<Factory> entityClass, Serializable id) {
		
		return baseDao.get(entityClass, id);
	}

	@Override
	public Page<Factory> findPage(String hql, Page<Factory> page, Class<Factory> entityClass, Object[] params) {
		baseDao.findPage(hql, page, entityClass, params);
		return page;
	}

	@Override
	public void saveOrUpdate(Factory entity) {
		if(UtilFuns.isEmpty(entity.getId())){
			//判断id是否有值
			entity.setId(UUID.randomUUID().toString().replace("-", ""));
			//说明id没有值，说明是保存
			entity.setState("1");
		}
		baseDao.saveOrUpdate(entity);
	}

	@Override
	public void saveOrUpdateAll(Collection<Factory> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}

	@Override
	public void deleteById(Class<Factory> entityClass, Serializable id) {
		baseDao.deleteById(entityClass, id);
	}

	@Override
	public void delete(Class<Factory> entityClass, Serializable[] ids) {
		baseDao.delete(entityClass, ids);
	}

	

}
