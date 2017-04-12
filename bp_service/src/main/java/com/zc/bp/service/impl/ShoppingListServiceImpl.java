package com.zc.bp.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import com.zc.bp.dao.BaseDao;
import com.zc.bp.domain.ShoppingList;
import com.zc.bp.service.ShoppingListService;
import com.zc.bp.utils.Page;
import com.zc.bp.utils.UtilFuns;

public class ShoppingListServiceImpl implements ShoppingListService{
	

	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<ShoppingList> find(String hql, Class<ShoppingList> entityClass, Object[] params) {
		return baseDao.find(hql, entityClass, params);
	}

	@Override
	public ShoppingList get(Class<ShoppingList> entityClass, Serializable id) {
		return baseDao.get(entityClass, id);
	}

	@Override
	public Page<ShoppingList> findPage(String hql, Page<ShoppingList> page, Class<ShoppingList> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, entityClass, params);
	}

	@Override
	public void saveOrUpdate(final ShoppingList entity) {
		if(UtilFuns.isEmpty(entity.getSid())){
			//判断id是否有值
			//说明id没有值，说明是保存
			String replace = UUID.randomUUID().toString().replace("-", "");
			entity.setSid(replace);
			
		}
		baseDao.saveOrUpdate(entity);
	}

	@Override
	public void saveOrUpdateAll(Collection<ShoppingList> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}

	@Override
	public void deleteById(Class<ShoppingList> entityClass, Serializable id) {
		baseDao.deleteById(entityClass, id);
	}

	@Override
	public void delete(Class<ShoppingList> entityClass, Serializable[] ids) {
		baseDao.delete(entityClass, ids);
	}

}
