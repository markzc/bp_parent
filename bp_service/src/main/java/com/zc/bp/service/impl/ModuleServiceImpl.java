package com.zc.bp.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.zc.bp.dao.BaseDao;
import com.zc.bp.domain.Dept;
import com.zc.bp.domain.Module;
import com.zc.bp.service.ModuleService;
import com.zc.bp.utils.Page;
import com.zc.bp.utils.UtilFuns;

public class ModuleServiceImpl implements ModuleService {
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<Module> find(String hql, Class<Module> entityClass, Object[] params) {
		return baseDao.find(hql, entityClass, params);
	}

	@Override
	public Module get(Class<Module> entityClass, Serializable id) {
		return baseDao.get(entityClass, id);
	}

	@Override
	public Page<Module> findPage(String hql, Page<Module> page, Class<Module> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, entityClass, params);
	}

	@Override
	public void saveOrUpdate(Module entity) {
		if(UtilFuns.isEmpty(entity.getId())){
			entity.setState(1L);
		}
		baseDao.saveOrUpdate(entity);
	}

	@Override
	public void saveOrUpdateAll(Collection<Module> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}

	@Override
	/**
	 * 递归删除
	 */
	public void deleteById(Class<Module> entityClass, Serializable id) {
		List<Module> moduleList = find("from Module where parent.id=?",Module.class, new Serializable[]{id});
		if(moduleList!=null && moduleList.size()!=0){
			for (Module module : moduleList) {
				deleteById(Module.class,module.getId());
			}
		}
		baseDao.deleteById(entityClass, id);
	}

	@Override
	public void delete(Class<Module> entityClass, Serializable[] ids) {
		for (Serializable id : ids) {
			deleteById(entityClass,id);
		}
	}

}
