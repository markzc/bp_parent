package com.zc.bp.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.zc.bp.dao.BaseDao;
import com.zc.bp.domain.Contract;
import com.zc.bp.domain.ContractProduct;
import com.zc.bp.domain.ExtCproduct;
import com.zc.bp.service.ContractProductService;
import com.zc.bp.utils.Page;
import com.zc.bp.utils.UtilFuns;

public class ContractProductServiceImpl implements ContractProductService {
	
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<ContractProduct> find(String hql, Class<ContractProduct> entityClass, Object[] params) {
		
		return baseDao.find(hql, entityClass, params);
	}

	@Override
	public ContractProduct get(Class<ContractProduct> entityClass, Serializable id) {
		
		return baseDao.get(entityClass, id);
	}

	@Override
	public Page<ContractProduct> findPage(String hql, Page<ContractProduct> page, Class<ContractProduct> entityClass, Object[] params) {
		baseDao.findPage(hql, page, entityClass, params);
		return page;
	}

	@Override
	public void saveOrUpdate(ContractProduct entity) {
		double amount=0d;
		if(UtilFuns.isEmpty(entity.getId())){
			if(UtilFuns.isNotEmpty(entity.getCnumber()) && UtilFuns.isNotEmpty(entity.getPrice()) ){
				amount=entity.getCnumber()*entity.getPrice();
				entity.setAmount(amount);
			}
			Contract contract = baseDao.get(Contract.class,entity.getContract().getId());
			contract.setTotalAmount(contract.getTotalAmount()+amount);
			baseDao.saveOrUpdate(contract);//可以不写,因为Hibernate的快照机制
		}else{
			double oldAmount = entity.getAmount();
			if(UtilFuns.isNotEmpty(entity.getCnumber()) && UtilFuns.isNotEmpty(entity.getPrice()) ){
				amount=entity.getCnumber()*entity.getPrice();
				entity.setAmount(amount);
			}
			Contract contract = baseDao.get(Contract.class,entity.getContract().getId());
			
			contract.setTotalAmount(contract.getTotalAmount()+amount-oldAmount);
			baseDao.saveOrUpdate(contract);
		}
		baseDao.saveOrUpdate(entity);
	}

	@Override
	public void saveOrUpdateAll(Collection<ContractProduct> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}

	
	@Override
	public void deleteById(Class<ContractProduct> entityClass, Serializable id) {
		ContractProduct contractProduct = baseDao.get(ContractProduct.class, id);
		Contract contract = contractProduct.getContract();
		
		Set<ExtCproduct> extCproducts = contractProduct.getExtCproducts();
		for (ExtCproduct extCproduct : extCproducts) {
			contract.setTotalAmount(contract.getTotalAmount()-extCproduct.getAmount());
		}
		
		contract.setTotalAmount(contract.getTotalAmount()-contractProduct.getAmount());
		
		baseDao.saveOrUpdate(contract);
		
		baseDao.deleteById(entityClass, id);
	}

	@Override
	public void delete(Class<ContractProduct> entityClass, Serializable[] ids) {
		baseDao.delete(entityClass, ids);
	}


}
