package com.zc.bp.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import com.zc.bp.dao.BaseDao;
import com.zc.bp.domain.Invoice;
import com.zc.bp.service.InvoiceService;
import com.zc.bp.utils.Page;
import com.zc.bp.utils.UtilFuns;

public class InvoiceServiceImpl implements InvoiceService {

	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<Invoice> find(String hql, Class<Invoice> entityClass, Object[] params) {
		
		return baseDao.find(hql, entityClass, params);
	}

	@Override
	public Invoice get(Class<Invoice> entityClass, Serializable id) {
		
		return baseDao.get(entityClass, id);
	}

	@Override
	public Page<Invoice> findPage(String hql, Page<Invoice> page, Class<Invoice> entityClass, Object[] params) {
		baseDao.findPage(hql, page, entityClass, params);
		return page;
	}

	@Override
	public void saveOrUpdate(Invoice entity) {
		if(UtilFuns.isEmpty(entity.getId())){
			//判断id是否有值
			
			//说明id没有值，说明是保存
			String id = UUID.randomUUID().toString();
			entity.setId(id);
		}
		baseDao.saveOrUpdate(entity);
	}

	@Override
	public void saveOrUpdateAll(Collection<Invoice> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}
	@Override
	public void deleteById(Class<Invoice> entityClass, Serializable id) {
		baseDao.deleteById(entityClass, id);
	}

	@Override
	public void delete(Class<Invoice> entityClass, Serializable[] ids) {
		baseDao.delete(entityClass, ids);
	}

}
