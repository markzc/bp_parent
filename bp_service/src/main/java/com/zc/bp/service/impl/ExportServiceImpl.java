package com.zc.bp.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.zc.bp.dao.BaseDao;
import com.zc.bp.domain.Contract;
import com.zc.bp.domain.ContractProduct;
import com.zc.bp.domain.Export;
import com.zc.bp.domain.ExportProduct;
import com.zc.bp.domain.ExtCproduct;
import com.zc.bp.domain.ExtEproduct;
import com.zc.bp.service.ExportService;
import com.zc.bp.utils.Page;
import com.zc.bp.utils.UtilFuns;


public class ExportServiceImpl implements ExportService {
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<Export> find(String hql, Class<Export> entityClass, Object[] params) {
		return baseDao.find(hql, entityClass, params);
	}

	@Override
	public Export get(Class<Export> entityClass, Serializable id) {
		return baseDao.get(entityClass, id);
	}

	@Override
	public Page<Export> findPage(String hql, Page<Export> page, Class<Export> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, entityClass, params);
	}

	@Override
	public void saveOrUpdate(Export model) {
		if(UtilFuns.isEmpty(model.getId())){
			//先给报运单设置状态
			model.setState(0);
			model.setInputDate(new Date());
			//新增
			String[] contractIds = model.getContractIds().split(", ");
			StringBuffer sb = new StringBuffer();
			for (String id : contractIds) {
				
				//根据id查出购销合同
				Contract contract = baseDao.get(Contract.class,id);
				//该表购销合同的状态
				contract.setState(2);
				
				baseDao.saveOrUpdate(contract);
				//拼接合同号
				sb.append(contract.getContractNo()).append(" ");
				
			}
			model.setCustomerContract(sb.toString());
			//购销合同编号集合
			model.setContractIds(UtilFuns.joinStr(contractIds,","));
			
			//获得合同下货物
			String hql="from ContractProduct where contract.id in ("+UtilFuns.joinInStr(contractIds)+")";
			List<ContractProduct> lists = baseDao.find(hql, ContractProduct.class, null);
			
			//新建一个报运单下的货物集合来接受货物
			Set<ExportProduct> sets = new HashSet<>();
			//给set集合赋值
			for (ContractProduct cp : lists) {
				ExportProduct ep = new ExportProduct();
				ep.setBoxNum(cp.getBoxNum());
				ep.setCnumber(cp.getCnumber());
				ep.setExport(model);//设置商品与报运单的关系      多对一  这里要让多的一方放弃主键的维护权inverse='true' 要不会发生冲突
				ep.setFactory(cp.getFactory());
				ep.setOrderNo(cp.getOrderNo());
				ep.setPackingUnit(cp.getPackingUnit());
				ep.setPrice(cp.getPrice());
				ep.setProductNo(cp.getProductNo());
				
				sets.add(ep);
					
				//加载购销合同下的货物
				Set<ExtCproduct> extcs = cp.getExtCproducts();
				//创建报运单下的货物
				Set<ExtEproduct> extes = new HashSet<>();
				
				//数据搬家
				for (ExtCproduct extc : extcs) {
					ExtEproduct exte = new ExtEproduct();
					//属性copy
					BeanUtils.copyProperties(extc,exte);
					//把没有对应的属性设置上
					exte.setId(null);  //会在dao中由Hibernate自动生成
					exte.setExportProduct(ep);  //记得一方放弃主键
					extes.add(exte);
				}
				//把附件扔进商品中
				ep.setExtEproducts(extes);
			}
			//把商品扔进报运单中
			model.setExportProducts(sets);
		}
		
		baseDao.saveOrUpdate(model);//会有两个级联保存
	}


	@Override
	/**
	 * 递归删除
	 */
	public void deleteById(Class<Export> entityClass, Serializable id) {
		baseDao.deleteById(entityClass, id);
	}

	@Override
	public void delete(Class<Export> entityClass, Serializable[] ids) {
		baseDao.delete(entityClass, ids);
	}

}
