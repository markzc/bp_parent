package com.zc.bp.web.cargo;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ModelDriven;
import com.sun.xml.bind.v2.model.core.ID;
import com.zc.bp.domain.Contract;
import com.zc.bp.domain.Export;
import com.zc.bp.domain.ExportProduct;
import com.zc.bp.domain.Invoice;
import com.zc.bp.domain.PackingList;
import com.zc.bp.domain.User;
import com.zc.bp.service.ContractService;
import com.zc.bp.service.ExportProductService;
import com.zc.bp.service.ExportService;
import com.zc.bp.service.PackingListService;
import com.zc.bp.utils.Page;
import com.zc.bp.utils.UtilFuns;
import com.zc.bp.web.BaseAction;

import cn.itcast.export.webservice.EpService;

public class ExportAction extends BaseAction implements ModelDriven<Export>{
	private Export model = new Export();
	
	public Export getModel() {
		return model;
	}
	
	//引入service
	private ExportService exportService;
	public void setExportService(ExportService exportService) {
		this.exportService = exportService;
	}
	private ContractService contractService;
	public void setContractService(ContractService contractService) {
		this.contractService = contractService;
	}
	private ExportProductService exportProductService;
	public void setExportProductService(ExportProductService exportProductService) {
		this.exportProductService = exportProductService;
	}
	private PackingListService packingListService;
	public void setPackingListService(PackingListService packingListService) {
		this.packingListService = packingListService;
	}

	//分页组件
	private Page page = new Page();
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	
	/**
	 * 打印出口报运单
	 * @throws Exception 
	 */
	public String printExport() throws Exception{
		Export export = exportService.get(Export.class, model.getId());
		String path = ServletActionContext.getServletContext().getRealPath("/");
		DuCheng duCheng = new DuCheng();
		duCheng.printExport(export,path, ServletActionContext.getResponse());
		return NONE;
	}
	
	
	/**
	 * 打印购销合同
	 * @return
	 * @throws Exception 
	 */
	public String print() throws Exception{
		List<Contract> contract = contractService.find("from Contract where id=?",Contract.class,new String[]{model.getId()});
		String path = ServletActionContext.getServletContext().getRealPath("/");
		DuCheng duCheng = new DuCheng();
		duCheng.print(contract.get(0), path, ServletActionContext.getResponse());
		return NONE;
	}
	
	
	/**
	 * 分页查询
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		String hql ="from Export where 1=1 ";
		//==================细粒度权限控制start
		
		//User user = super.getCurUser();
		//int degree =user.getUserInfo().getDegree();//获取用户的级别
		/*if(degree==4){
			//员工
			hql+=" and createBy='"+user.getId()+"'";
		}else if(degree == 3){
			//管理本部门
			hql+= " and createDept='"+user.getDept().getId()+"'";
		}else if(degree == 2){
			//管理本部门及下属部门
		}else if(degree == 1){
			//跨部门跨人员的管理
		}else if(degree ==0){
			//总裁
			//不加条件
		}*/
		
		//=======================细粒度权限控制end
		//1.调用业务逻辑，实现分页查询
		exportService.findPage(hql, page, Export.class, null);
		//2.设置url
		page.setUrl("exportAction_list");//相对路径 
		//3.将分页查询的结果，放入值栈中，放入栈顶
		super.push(page);
		//4.跳页面
		return "list";
	}
	/**
	 * 查询状态为1的购销合同
	 * @return
	 * @throws Exception
	 */
	public String contractList()throws Exception{
		//1.查询状态为1的购销合同
		contractService.findPage("from Contract where state=1", page, Contract.class, null);
		
		page.setUrl("exportAction_contractList");
		//2.放入值栈
		super.push(page);
		return "contractList";
	}
	
	/**
	 * 查看
	 * model对象   Dept类型
	 *    id属性
	 */
	public String toview() throws Exception {
		//1.根据id,加载对象
		Export obj = exportService.get(Export.class, model.getId());
		//2.将对象放入值栈
		super.push(obj);
		//查出要修改的商品列表
		Set<ExportProduct> sets = obj.getExportProducts();
		//遍历商品列表
		StringBuffer sb = new StringBuffer();
		for (ExportProduct ep : sets) {
			sb.append("addTRRecord(\"mRecordTable\", \"").append(ep.getId());
			sb.append("\", \"").append(UtilFuns.convertNull(ep.getProductNo()));
			sb.append("\", \"").append(UtilFuns.convertNull(ep.getCnumber()));
    		sb.append("\", \"").append(UtilFuns.convertNull(ep.getGrossWeight()));
    		sb.append("\", \"").append(UtilFuns.convertNull(ep.getNetWeight()));
    		sb.append("\", \"").append(UtilFuns.convertNull(ep.getSizeLength()));
    		sb.append("\", \"").append(UtilFuns.convertNull(ep.getSizeWidth()));
    		sb.append("\", \"").append(UtilFuns.convertNull(ep.getSizeHeight()));
    		sb.append("\", \"").append(UtilFuns.convertNull(ep.getExPrice()));
    		sb.append("\", \"").append(UtilFuns.convertNull(ep.getTax())).append("\");");
		}
    	super.put("mRecordData",sb.toString());
		//3.跳页面
		return "toview";
	}
	
	
	/**
	 * 报运
	 * @return
	 * @throws Exception
	 */
	public String tocreate() throws Exception {
		super.put("id",model.getId());
		return "tocreate";
	}
	/**
	 * 实现报运
	 * @return
	 */
	public String insert() throws Exception {
		//获得需要报运的购销合同的id
		User user = super.getCurUser();
		model.setCreateBy(user.getId());
		model.setCreateDept(user.getCreateDept());
		String[] split = model.getContractIds().split(",");
		double price=0;
		for (String s : split) {
			Contract contract = contractService.get(Contract.class,s );
			Double totalAmount = contract.getTotalAmount();
			price += totalAmount;
		}
		model.setPriceCondition(price+"");
		exportService.saveOrUpdate(model);

		return "alist";
	}
	
	/**
	 * 进入更新页面
	 * addTRRecord("mRecordTable", "id", "productNo", "cnumber", "grossWeight", "netWeight", "sizeLength", "sizeWidth", "sizeHeight", "exPrice", "tax");
	 */
	public String toupdate() throws Exception {
		Export obj = exportService.get(Export.class, model.getId());
		super.push(obj);		
		//查出要修改的商品列表
		Set<ExportProduct> sets = obj.getExportProducts();
		//遍历商品列表
		StringBuffer sb = new StringBuffer();
		for (ExportProduct ep : sets) {
			sb.append("addTRRecord(\"mRecordTable\", \"").append(ep.getId());
			sb.append("\", \"").append(UtilFuns.convertNull(ep.getProductNo()));
			sb.append("\", \"").append(UtilFuns.convertNull(ep.getCnumber()));
    		sb.append("\", \"").append(UtilFuns.convertNull(ep.getGrossWeight()));
    		sb.append("\", \"").append(UtilFuns.convertNull(ep.getNetWeight()));
    		sb.append("\", \"").append(UtilFuns.convertNull(ep.getSizeLength()));
    		sb.append("\", \"").append(UtilFuns.convertNull(ep.getSizeWidth()));
    		sb.append("\", \"").append(UtilFuns.convertNull(ep.getSizeHeight()));
    		sb.append("\", \"").append(UtilFuns.convertNull(ep.getExPrice()));
    		sb.append("\", \"").append(UtilFuns.convertNull(ep.getTax())).append("\");");
		}
    	super.put("mRecordData",sb.toString());
		return "toupdate";
	}
	
	/**
	 * 实现更新操作
	 */
	public String update() throws Exception {

		
		//1.根据id,加载原有的部门对象
    	Export obj = exportService.get(Export.class, model.getId());
    	
    	//2.设置要修改的属性
    	obj.setInputDate(model.getInputDate());
    	obj.setLcno(model.getLcno());
    	obj.setConsignee(model.getConsignee());
    	obj.setShipmentPort(model.getShipmentPort());
    	obj.setDestinationPort(model.getDestinationPort());
    	obj.setTransportMode(model.getTransportMode());
    	obj.setPriceCondition(model.getPriceCondition());
    	obj.setMarks(model.getMarks());
    	obj.setRemark(model.getRemark());
    	
    	//修改商品列表
    	Set<ExportProduct> sets = new HashSet<ExportProduct>();
    	for (int i = 0 ; i < mr_id.length ; i++) {
			//得到每个商品的id
    		ExportProduct ep = exportProductService.get(ExportProduct.class, mr_id[i]);
    		if("1".equals(mr_changed[i])){
    			//说明当前行的值有改变
    			ep.setCnumber(mr_cnumber[i]);
    			ep.setGrossWeight(mr_grossWeight[i]);
    			ep.setNetWeight(mr_netWeight[i]);
    			ep.setSizeLength(mr_sizeLength[i]);
    			ep.setSizeWidth(mr_sizeWidth[i]);
    			ep.setSizeHeight(mr_sizeHeight[i]);
    			ep.setExPrice(mr_exPrice[i]);
    			ep.setTax(mr_tax[i]);
    		}
    		sets.add(ep);//商品列表中添加了一个商品
		}
    	
    	obj.setExportProducts(sets);
	
		//3.调用业务方法，实现更新操作 
		exportService.saveOrUpdate(obj);
		//4.跳页面
		return "slist";
	}
	
	private String[] mr_id;
    private String[] mr_changed;
    private Integer[] mr_cnumber;
    private Double[] mr_grossWeight;
    private Double[] mr_netWeight;
    private Double[] mr_sizeLength;
    private Double[] mr_sizeWidth;
    private Double[] mr_sizeHeight;
    private Double[] mr_exPrice;
    private Double[] mr_tax;
	public void setMr_id(String[] mr_id) {
		this.mr_id = mr_id;
	}
	public void setMr_changed(String[] mr_changed) {
		this.mr_changed = mr_changed;
	}
	public void setMr_cnumber(Integer[] mr_cnumber) {
		this.mr_cnumber = mr_cnumber;
	}
	public void setMr_grossWeight(Double[] mr_grossWeight) {
		this.mr_grossWeight = mr_grossWeight;
	}
	public void setMr_netWeight(Double[] mr_netWeight) {
		this.mr_netWeight = mr_netWeight;
	}
	public void setMr_sizeLength(Double[] mr_sizeLength) {
		this.mr_sizeLength = mr_sizeLength;
	}
	public void setMr_sizeWidth(Double[] mr_sizeWidth) {
		this.mr_sizeWidth = mr_sizeWidth;
	}
	public void setMr_sizeHeight(Double[] mr_sizeHeight) {
		this.mr_sizeHeight = mr_sizeHeight;
	}
	public void setMr_exPrice(Double[] mr_exPrice) {
		this.mr_exPrice = mr_exPrice;
	}
	public void setMr_tax(Double[] mr_tax) {
		this.mr_tax = mr_tax;
	}
	/**
	 * 实现删除操作
	 */
	public String delete() throws Exception {
		//1.切割字符串
		String ids [] = model.getId().split(", ");
		
		//2.调用业务方法，实现批量删除
		exportService.delete(Export.class, ids);
		
		//3.跳页面
		return "slist";
	}
	
	private EpService weatherClient;
	public void setWeatherClient(EpService weatherClient) {
			this.weatherClient = weatherClient;
		}
	/**
	 * 电子报运
	 * @throws Exception 
	 */
	public String exportE() throws Exception{
		Export export = exportService.get(Export.class, model.getId());
		StringBuffer sb = new StringBuffer();
		sb.append("{\"exportId\":\""+export.getId()+"\",");
		sb.append("\"inputDate\":\""+export.getInputDate()+"\",");
		sb.append("\"shipmentPort\":\""+export.getShipmentPort()+"\",");
		sb.append("\"destinationPort\":\""+export.getDestinationPort()+"\",");
		sb.append("\"transportMode\":\""+export.getTransportMode()+"\",");
		sb.append("\"priceCondition\":\""+export.getPriceCondition()+"\",");
		sb.append("\"boxNums\":\""+export.getBoxNums()+"\",");
		sb.append("\"grossWeights\":\""+export.getGrossWeights()+"\",");
		sb.append("\"priceCondition\":\""+export.getPriceCondition()+"\",");
		sb.append("\"measurements\":\""+export.getMeasurements()+"\",");
		sb.append("\"reason\":\""+export.getRemark()+"\",");
		sb.append("\"products\":[");
		Set<ExportProduct> eps = export.getExportProducts();
		for (ExportProduct ep : eps) {
			sb.append("{");
			sb.append("\"exportProductId\":\""+ep.getId()+"\",");
			sb.append("\"productNo\":\""+ep.getProductNo()+"\",");
			sb.append("\"packingUnit\":\""+ep.getPackingUnit()+"\",");
			sb.append("\"cnumber\":\""+ep.getCnumber()+"\",");
			sb.append("\"boxNum\":\""+ep.getBoxNum()+"\",");
			sb.append("\"grossWeight\":\""+ep.getGrossWeight()+"\",");
			sb.append("\"netWeight\":\""+ep.getNetWeight()+"\",");
			sb.append("\"sizeLength\":\""+ep.getSizeLength()+"\",");
			sb.append("\"sizeWidth\":\""+ep.getSizeWidth()+"\",");
			sb.append("\"sizeHeight\":\""+ep.getSizeHeight()+"\",");
			sb.append("\"exPrice\":\""+ep.getExPrice()+"\",");
			sb.append("\"price\":\""+ep.getPrice()+"\",");
			sb.append("\"tax\":\""+ep.getTax()+"\",");
			sb.append("\"orderNo\":\""+ep.getOrderNo()+"\",");
			sb.append("\"factoryId\":\""+ep.getFactory().getId()+"\"");
			sb.append("},");
			
		}
		//擦出最后一个,
		sb.delete(sb.length()-1, sb.length());
		sb.append("]}");
		String exportE=null;
		try {
			exportE = weatherClient.exportE(sb.toString());
		} catch (Exception e) {
			changeState(1);
			e.printStackTrace();
		}
		Export export02 = JSON.parseObject(exportE, Export.class);
		update(export);
		changeState(2);
		return "slist";
	}
	/**
	 * ws成功后的更新操作
	 * @param exportE
	 */
	private void update(Export export) {
		//生成装箱单
		PackingList packingList = new PackingList();
		packingList.setSeller("杰信");
		packingList.setInvoiceNo(UUID.randomUUID().toString().replace("-", ""));
		Date date = new Date();
		packingList.setInvoiceDate(date);
		packingList.setBuyer(export.getConsignee());
		packingList.setMarks(export.getMarks());
		packingList.setDescriptions(export.getRemark());
		packingList.setExportIds(export.getId());
		packingList.setExportNos(export.getLcno());
		packingList.setState(0);
		packingListService.saveOrUpdate(packingList);
		
	}
	/**
	 * 购销合同提交操作
	 * @return
	 * @throws Exception
	 */
	/*public String submit() throws Exception {
		changeState(1);
		return "slist";
	}*/
	
	/**
	 * 购销合同取消操作 state=0
	 * @return
	 * @throws Exception
	 */
	public String cancel() throws Exception {
		changeState(0);
		return "slist";
	}
	
	//修改状态  抽取方法:ALT+SHIFT+M
	private void changeState(int state) {
		//1.得到用户所选中的多个购销合同的id
		String ids [] = model.getId().split(", ");
		
		//2.遍历ids数组，得到每个购销合同对象
		for(String id :ids){
			//根据购销合同的id,加载购销合同对象
			Export contract = exportService.get(Export.class, id);
			
			//修改状态 
			contract.setState(state);
			
			//保存更新的结果信息
			exportService.saveOrUpdate(contract);
		}
	}

}