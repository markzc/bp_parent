package com.zc.bp.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import com.zc.bp.dao.BaseDao;
import com.zc.bp.domain.PackingList;
import com.zc.bp.service.PackingListService;
import com.zc.bp.utils.Page;
import com.zc.bp.utils.UtilFuns;

public class PackingListServiceImpl implements PackingListService {
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<PackingList> find(String hql, Class<PackingList> entityClass, Object[] params) {
		
		return baseDao.find(hql, entityClass, params);
	}

	@Override
	public PackingList get(Class<PackingList> entityClass, Serializable id) {
		
		return baseDao.get(entityClass, id);
	}

	@Override
	public Page<PackingList> findPage(String hql, Page<PackingList> page, Class<PackingList> entityClass, Object[] params) {
		baseDao.findPage(hql, page, entityClass, params);
		return page;
	}

	@Override
	public void saveOrUpdate(PackingList entity) {
		if(UtilFuns.isEmpty(entity.getId())){
			//判断id是否有值
			
			//说明id没有值，说明是保存
			String id = UUID.randomUUID().toString().replace("-", "");
			entity.setId(id);
			
		}
		baseDao.saveOrUpdate(entity);
	}
 
	@Override
	public void saveOrUpdateAll(Collection<PackingList> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}
	@Override
	public void deleteById(Class<PackingList> entityClass, Serializable id) {
		baseDao.deleteById(entityClass, id);
	}

	@Override
	public void delete(Class<PackingList> entityClass, Serializable[] ids) {
		baseDao.delete(entityClass, ids);
	}
}
