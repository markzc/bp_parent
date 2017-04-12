package com.zc.bp.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import com.zc.bp.dao.BaseDao;
import com.zc.bp.domain.Finance;
import com.zc.bp.domain.Finance;
import com.zc.bp.service.FinanceService;
import com.zc.bp.utils.Page;
import com.zc.bp.utils.UtilFuns;

public class FinanceServiceImpl implements FinanceService {

	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<Finance> find(String hql, Class<Finance> entityClass, Object[] params) {
		
		return baseDao.find(hql, entityClass, params);
	}

	@Override
	public Finance get(Class<Finance> entityClass, Serializable id) {
		
		return baseDao.get(entityClass, id);
	}

	@Override
	public Page<Finance> findPage(String hql, Page<Finance> page, Class<Finance> entityClass, Object[] params) {
		baseDao.findPage(hql, page, entityClass, params);
		return page;
	}

	@Override
	public void saveOrUpdate(Finance entity) {
		if(UtilFuns.isEmpty(entity.getId())){
			//判断id是否有值
			
			//说明id没有值，说明是保存
			String id = UUID.randomUUID().toString().replace("-", "");
			entity.setId(id);
		}
		baseDao.saveOrUpdate(entity);
	}

	@Override
	public void saveOrUpdateAll(Collection<Finance> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}
	@Override
	public void deleteById(Class<Finance> entityClass, Serializable id) {
		baseDao.deleteById(entityClass, id);
	}

	@Override
	public void delete(Class<Finance> entityClass, Serializable[] ids) {
		baseDao.delete(entityClass, ids);
	}

}
