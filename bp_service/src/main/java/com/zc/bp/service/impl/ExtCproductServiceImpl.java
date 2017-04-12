package com.zc.bp.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.zc.bp.dao.BaseDao;
import com.zc.bp.domain.Contract;
import com.zc.bp.domain.ContractProduct;
import com.zc.bp.domain.ExtCproduct;
import com.zc.bp.service.ExtCproductService;
import com.zc.bp.utils.Page;
import com.zc.bp.utils.UtilFuns;

public class ExtCproductServiceImpl implements ExtCproductService {
	
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<ExtCproduct> find(String hql, Class<ExtCproduct> entityClass, Object[] params) {
		
		return baseDao.find(hql, entityClass, params);
	}

	@Override
	public ExtCproduct get(Class<ExtCproduct> entityClass, Serializable id) {
		
		return baseDao.get(entityClass, id);
	}

	@Override
	public Page<ExtCproduct> findPage(String hql, Page<ExtCproduct> page, Class<ExtCproduct> entityClass, Object[] params) {
		baseDao.findPage(hql, page, entityClass, params);
		return page;
	}

	@Override
	public void saveOrUpdate(ExtCproduct entity) {
		double amount=0d;
		//判断id是否有值,id没有值，说明是保存
		if(UtilFuns.isEmpty(entity.getId())){
			if(UtilFuns.isNotEmpty(entity.getCnumber())&&UtilFuns.isNotEmpty(entity.getPrice())){
				amount = entity.getCnumber()*entity.getPrice();
			}
			entity.setAmount(amount);
			Contract contract = baseDao.get(Contract.class, entity.getContractProduct().getContract().getId());
			contract.setTotalAmount(contract.getTotalAmount()+amount);
		}else{
			//如果是修改就得先减去以前的附件金额在加上最新的
			double oldAmount=entity.getAmount();
			if(UtilFuns.isNotEmpty(entity.getCnumber())&&UtilFuns.isNotEmpty(entity.getPrice())){
				amount = entity.getCnumber()*entity.getPrice();
			}
			entity.setAmount(amount);
			Contract contract = baseDao.get(Contract.class, entity.getContractProduct().getContract().getId());
			contract.setTotalAmount(contract.getTotalAmount()+amount-oldAmount);
			baseDao.saveOrUpdate(contract);//可以不写，因为Hibernate的快照机制
		}
		baseDao.saveOrUpdate(entity);
	}

	@Override
	public void deleteById(Class<ExtCproduct> entityClass, ExtCproduct extCproduct) {
		//删除的时候先把合同上的总金额减去要删除的附件的总金额
		//对象导航查询出附件的总金额
		ExtCproduct extCproduct2 = baseDao.get(ExtCproduct.class, extCproduct.getId());
		double amount = extCproduct2.getAmount();
		//对象导航查询出合同的金额
		Contract contract = baseDao.get(Contract.class, extCproduct.getContractProduct().getContract().getId());
		double contractAmount = contract.getTotalAmount();
		contract.setTotalAmount(contractAmount-amount);
		//保存新的合同
		baseDao.saveOrUpdate(contract);
		//删除附件
		baseDao.deleteById(entityClass, extCproduct.getId());
	}




}
