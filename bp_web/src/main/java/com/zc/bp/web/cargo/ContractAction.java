package com.zc.bp.web.cargo;

import java.util.List;


import com.opensymphony.xwork2.ModelDriven;
import com.zc.bp.domain.Contract;
import com.zc.bp.domain.Dept;
import com.zc.bp.domain.User;
import com.zc.bp.exception.SysException;
import com.zc.bp.service.ContractService;
import com.zc.bp.service.DeptService;
import com.zc.bp.utils.Page;
import com.zc.bp.web.BaseAction;

public class ContractAction extends BaseAction implements ModelDriven<Contract>{

	private Contract model=new Contract();
	@Override
	public Contract getModel() {
		return model;
	}
	
	//分组插件
	private Page page = new Page();
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	
	private DeptService deptService;
	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}

	private ContractService contractService;
	public void setContractService(ContractService contractService) {
		this.contractService = contractService;
	}
	/**
	 * 分页显示
	 * @return
	 */
	public String list(){
		String hql="from Contract where 1=1 ";
		//细粒度控制权限
		User user = super.getCurUser();
		int degree = user.getUserInfo().getDegree();
		if(degree==4){
			//员工
			hql+="and createBy='"+user.getId()+"'";
		}else if(degree==3){
			//部门经理（管理本部门）
			hql+="and createDept='"+user.getDept().getId()+"'";
		}else if(degree==2){
			//管理本部门及下属部门
			hql+="and createDept='"+user.getDept().getId()+"'";
			List<Dept> dept =  deptService.find("from Dept where parent.id=?", Dept.class, new String[]{user.getDept().getId()});
			for (Dept dept2 : dept) {
				hql+=" or createDept='"+dept2.getId()+"'";
			}
		}else if(degree==1){
			//副总
			hql+="and createDept='"+user.getDept().getId()+"'";
			List<Dept> dept =  deptService.find("from Dept where parent.id=?", Dept.class, new String[]{user.getDept().getId()});
			for (Dept dept2 : dept) {
				hql+=" and createDept='"+dept2+"'";
			}
		}else if(degree==0){
			//总经理，不需要任何条件
		}

		contractService.findPage(hql,page,Contract.class,null);
		
		page.setUrl("contractAction_list"); //相对路劲
		super.push(page);
		return "list";
	}
	
	/**
	 * 查看单个
	 */
	public String toview(){
		Contract contract = contractService.get(Contract.class, model.getId());
		super.push(contract);
		return "toView";
	}
	
	/**
	 * 转到增加页面
	 */
	public String tocreate(){
		
		return "tocreate";
	}
	/**
	 * 添加
	 */
	public String insert(){
		User user = super.getCurUser();
		model.setCreateBy(user.getId());
		model.setCreateDept(user.getCreateDept());
		contractService.saveOrUpdate(model);
		return "alist";
	}
	/**
	 * 转到修改页面，数据回显
	 */
	public String toupdate(){
		Contract contract = contractService.get(Contract.class, model.getId());
		super.push(contract);
		return "toupdate";
	}
	
	/**
	 * 修改
	 */
	public String update(){
		Contract obj = contractService.get(Contract.class, model.getId());
		
		//把修改了什么写上
		obj.setCustomName(model.getCustomName());
    	obj.setPrintStyle(model.getPrintStyle());
    	obj.setContractNo(model.getContractNo());
    	obj.setOfferor(model.getOfferor());
    	obj.setInputBy(model.getInputBy());
    	obj.setCheckBy(model.getCheckBy());
        obj.setInspector(model.getInspector());
        obj.setSigningDate(model.getSigningDate());  
        obj.setImportNum(model.getImportNum());
        obj.setShipTime(model.getShipTime());
        obj.setTradeTerms(model.getTradeTerms());
        obj.setDeliveryPeriod(model.getDeliveryPeriod());
        obj.setCrequest(model.getCrequest());
        obj.setRemark(model.getRemark());
		
		contractService.saveOrUpdate(obj);
		return "alist";
	}
	
	/**
	 * 删除
	 * @throws SysException 
	 */
	public String delete() throws SysException{
		try {
			String[] idStrings = model.getId().split(", ");
			contractService.delete(Contract.class, idStrings);
			return "alist";
		} catch (Exception e) {
			e.printStackTrace();
			throw new SysException("先选中一个！！！");
		}
	}
	
	//批量提交
	public String submit() throws SysException{
		try {
			changeState(1);
			return "alist";
		} catch (Exception e) {
			e.printStackTrace();
			throw new SysException("先至少选中一个！！！");
		}
	}
	//批量取消
	public String cancel() throws SysException{
		try {
			changeState(0);
			return "alist";
		} catch (Exception e) {
			e.printStackTrace();
			throw new SysException("先至少选中一个！！！");
		}
	}
	//修改状态
	public void changeState (int state){
		String[] ids= model.getId().split(", ");
		for (String id : ids) {
			Contract obj = contractService.get(Contract.class,id);
			obj.setState(state);
			contractService.saveOrUpdate(obj);
		}
	}

}
