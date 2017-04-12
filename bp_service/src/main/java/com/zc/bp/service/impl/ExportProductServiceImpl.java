package com.zc.bp.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.zc.bp.dao.BaseDao;
import com.zc.bp.domain.ExportProduct;
import com.zc.bp.service.ExportProductService;
import com.zc.bp.utils.Page;
import com.zc.bp.utils.UtilFuns;

public class ExportProductServiceImpl implements ExportProductService {
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<ExportProduct> find(String hql, Class<ExportProduct> entityClass, Object[] params) {
		return baseDao.find(hql, entityClass, params);
	}

	@Override
	public ExportProduct get(Class<ExportProduct> entityClass, Serializable id) {
		return baseDao.get(entityClass, id);
	}

	@Override
	public Page<ExportProduct> findPage(String hql, Page<ExportProduct> page, Class<ExportProduct> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, entityClass, params);
	}

	@Override
	public void saveOrUpdate(ExportProduct entity) {
		if(UtilFuns.isEmpty(entity.getId())){
			
		}
		baseDao.saveOrUpdate(entity);
	}

	@Override
	public void saveOrUpdateAll(Collection<ExportProduct> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}

	@Override
	/**
	 * 递归删除
	 */
	public void deleteById(Class<ExportProduct> entityClass, Serializable id) {
		List<ExportProduct> moduleList = find("from ExportProduct where parent.id=?",ExportProduct.class, new Serializable[]{id});
		if(moduleList!=null && moduleList.size()!=0){
			for (ExportProduct module : moduleList) {
				deleteById(ExportProduct.class,module.getId());
			}
		}
		baseDao.deleteById(entityClass, id);
	}

	@Override
	public void delete(Class<ExportProduct> entityClass, Serializable[] ids) {
		for (Serializable id : ids) {
			deleteById(entityClass,id);
		}
	}

}
