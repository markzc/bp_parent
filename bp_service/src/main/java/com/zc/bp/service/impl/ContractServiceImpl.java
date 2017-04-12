package com.zc.bp.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.ui.Model;

import com.zc.bp.dao.BaseDao;
import com.zc.bp.domain.Contract;
import com.zc.bp.service.ContractService;
import com.zc.bp.utils.Page;
import com.zc.bp.utils.UtilFuns;

public class ContractServiceImpl implements ContractService {
	
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<Contract> find(String hql, Class<Contract> entityClass, Object[] params) {
		
		return baseDao.find(hql, entityClass, params);
	}

	@Override
	public Contract get(Class<Contract> entityClass, Serializable id) {
		
		return baseDao.get(entityClass, id);
	}

	@Override
	public Page<Contract> findPage(String hql, Page<Contract> page, Class<Contract> entityClass, Object[] params) {
		baseDao.findPage(hql, page, entityClass, params);
		return page;
	}

	@Override
	public void saveOrUpdate(Contract entity) {
		if(UtilFuns.isEmpty(entity.getId())){
			//判断id是否有值
			
			//说明id没有值，说明是保存
			entity.setState(0);//1代表可用
			entity.setTotalAmount(0d);
		}
		baseDao.saveOrUpdate(entity);
	}


	

	@Override
	public void delete(Class<Contract> entityClass, Serializable[] ids) {
		baseDao.delete(entityClass, ids);
	}
	
	



}
