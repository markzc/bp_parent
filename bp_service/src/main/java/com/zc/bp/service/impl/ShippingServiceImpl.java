package com.zc.bp.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import com.zc.bp.dao.BaseDao;
import com.zc.bp.domain.ShippingOrder;
import com.zc.bp.service.ShippingService;
import com.zc.bp.utils.Page;
import com.zc.bp.utils.UtilFuns;

public class ShippingServiceImpl implements ShippingService {

	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<ShippingOrder> find(String hql, Class<ShippingOrder> entityClass, Object[] params) {
		
		return baseDao.find(hql, entityClass, params);
	}

	@Override
	public ShippingOrder get(Class<ShippingOrder> entityClass, Serializable id) {
		
		return baseDao.get(entityClass, id);
	}

	@Override
	public Page<ShippingOrder> findPage(String hql, Page<ShippingOrder> page, Class<ShippingOrder> entityClass, Object[] params) {
		baseDao.findPage(hql, page, entityClass, params);
		return page;
	}

	@Override
	public void saveOrUpdate(ShippingOrder entity) {
		if(UtilFuns.isEmpty(entity.getId())){
			//判断id是否有值
			//说明id没有值，说明是保存
			String id = UUID.randomUUID().toString();
			entity.setId(id);
		}
		baseDao.saveOrUpdate(entity);
	}

	@Override
	public void saveOrUpdateAll(Collection<ShippingOrder> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}
	@Override
	public void deleteById(Class<ShippingOrder> entityClass, Serializable id) {
		baseDao.deleteById(entityClass, id);
	}

	@Override
	public void delete(Class<ShippingOrder> entityClass, Serializable[] ids) {
		baseDao.delete(entityClass, ids);
	}
}
