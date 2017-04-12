package com.zc.bp.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.zc.bp.dao.BaseDao;
import com.zc.bp.domain.ExtEproduct;
import com.zc.bp.service.ExtEproductService;
import com.zc.bp.utils.Page;
import com.zc.bp.utils.UtilFuns;

public class ExtEproductServiceImpl implements ExtEproductService {
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<ExtEproduct> find(String hql, Class<ExtEproduct> entityClass, Object[] params) {
		return baseDao.find(hql, entityClass, params);
	}

	@Override
	public ExtEproduct get(Class<ExtEproduct> entityClass, Serializable id) {
		return baseDao.get(entityClass, id);
	}

	@Override
	public Page<ExtEproduct> findPage(String hql, Page<ExtEproduct> page, Class<ExtEproduct> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, entityClass, params);
	}

	@Override
	public void saveOrUpdate(ExtEproduct entity) {
		if(UtilFuns.isEmpty(entity.getId())){
			
		}
		baseDao.saveOrUpdate(entity);
	}

	@Override
	public void saveOrUpdateAll(Collection<ExtEproduct> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}

	@Override
	/**
	 * 递归删除
	 */
	public void deleteById(Class<ExtEproduct> entityClass, Serializable id) {
		List<ExtEproduct> moduleList = find("from ExtEproduct where parent.id=?",ExtEproduct.class, new Serializable[]{id});
		if(moduleList!=null && moduleList.size()!=0){
			for (ExtEproduct module : moduleList) {
				deleteById(ExtEproduct.class,module.getId());
			}
		}
		baseDao.deleteById(entityClass, id);
	}

	@Override
	public void delete(Class<ExtEproduct> entityClass, Serializable[] ids) {
		for (Serializable id : ids) {
			deleteById(entityClass,id);
		}
	}

}
