package com.zc.bp.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.zc.bp.dao.BaseDao;
import com.zc.bp.domain.Dept;
import com.zc.bp.service.DeptService;
import com.zc.bp.utils.Page;
import com.zc.bp.utils.UtilFuns;

public class DeptServiceImpl implements DeptService {
	
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<Dept> find(String hql, Class<Dept> entityClass, Object[] params) {
		
		return baseDao.find(hql, entityClass, params);
	}

	@Override
	public Dept get(Class<Dept> entityClass, Serializable id) {
		
		return baseDao.get(entityClass, id);
	}

	@Override
	public Page<Dept> findPage(String hql, Page<Dept> page, Class<Dept> entityClass, Object[] params) {
		baseDao.findPage(hql, page, entityClass, params);
		return page;
	}

	@Override
	public void saveOrUpdate(Dept entity) {
		if(UtilFuns.isEmpty(entity.getId())){
			//判断id是否有值
			
			//说明id没有值，说明是保存
			entity.setState(1);//1代表可用
		}
		baseDao.saveOrUpdate(entity);
	}

	@Override
	public void saveOrUpdateAll(Collection<Dept> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}

	@Override
	/**
	 * 递归删除
	 */
	public void deleteById(Class<Dept> entityClass, Serializable id) {
		List<Dept> deptList = find("from Dept where parent.id=?",Dept.class, new Serializable[]{id});
		if(deptList!=null && deptList.size()!=0){
			for (Dept dept : deptList) {
				deleteById(Dept.class,dept.getId());
			}
		}
		baseDao.deleteById(entityClass, id);
	}

	@Override
	public void delete(Class<Dept> entityClass, Serializable[] ids) {
		for(Serializable id : ids) {
			deleteById(Dept.class, id);
		}
	}

}
