package com.zc.bp.web.cargo;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;

import com.zc.bp.domain.Contract;
import com.zc.bp.domain.ContractProduct;
import com.zc.bp.service.ContractProductService;
import com.zc.bp.service.ContractService;
import com.zc.bp.utils.DownloadUtil;
import com.zc.bp.utils.UtilFuns;
import com.zc.bp.web.BaseAction;

public class OutProductAction extends BaseAction {

	private String inputDate;

	public void setInputDate(String inputDate) {
		this.inputDate = inputDate;
	}

	// 注入Service
	private ContractProductService contractProductService;

	public void setContractProductService(ContractProductService contractProductService) {
		this.contractProductService = contractProductService;
	}

	/**
	 * 进入出货表的打印页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String toedit() throws Exception {
		return "print";
	}
	
	private ContractService contractService ; 
	public void setContractService(ContractService contractService) {
		this.contractService = contractService;
	}

	
	
	
	/**
	 * 模板打印
	 * @return
	 * @throws Exception
	 */
	public String print() throws Exception{
		
		//1. 制作模板文件，读取模板文件
		String path = ServletActionContext.getServletContext().getRealPath("/")+"make/xlsprint/tOUTPRODUCT.xls";
		//2. 得到一个输入流
		InputStream inputStream = new FileInputStream(path);
		//3. 读取工作簿 参数为模板文件的流
		Workbook wb = new HSSFWorkbook(inputStream);
		//4. 得到工作表
		Sheet sheet = wb.getSheetAt(0);

		// 定义一些公共变量
		int rowNo = 0, cellNo = 1;
		Row nRow = null;
		Cell nCell = null;

		// 5.=================================大标题======================
		nRow = sheet.getRow(rowNo++);// 第一行的行对象 rowNo++就会进入第二行
		// 产生单元格对象
		nCell= nRow.getCell(cellNo);
		// 设置单元格的内容
		nCell.setCellValue(inputDate.replace("-0", "-").replace("-","年")+"月份出货表");//2015-01格式
		// 6.==================================小标题===========================
		//小标题已经有了所以就不用再设置了直接跳过即可
		rowNo++;
		// 7.==================================内容 ==============================
		//读取第三行
		nRow = sheet.getRow(rowNo);
		//获取单元格的样式
		CellStyle customerCellStyle = nRow.getCell(cellNo++).getCellStyle();//客人
		CellStyle orderNoCellStyle = nRow.getCell(cellNo++).getCellStyle();//订单号
		CellStyle productNoCellStyle = nRow.getCell(cellNo++).getCellStyle();//货号
		CellStyle cNumberCellStyle = nRow.getCell(cellNo++).getCellStyle();//数量
		CellStyle factoryNameCellStyle = nRow.getCell(cellNo++).getCellStyle();//工厂
		CellStyle deliveryPeriodCellStyle = nRow.getCell(cellNo++).getCellStyle();//工厂交期
		CellStyle shipTimeCellStyle = nRow.getCell(cellNo++).getCellStyle();//船期
		CellStyle tradeTermsCellStyle = nRow.getCell(cellNo++).getCellStyle();//贸易条款
		

		String hql = "from ContractProduct where to_char(contract.shipTime,'yyyy-MM')='"+inputDate+"'";																								// 符合船期要求）
		List<ContractProduct> lists = contractProductService.find(hql, ContractProduct.class, null);
		//遍历货物列表
		for(ContractProduct cp :lists){
			nRow = sheet.createRow(rowNo++);//产生数据行 
			nRow.setHeightInPoints(24f);//行高
			
			cellNo=1;
			//"客户",
			nCell = nRow.createCell(cellNo++);
			nCell.setCellValue(cp.getContract().getCustomName());
			nCell.setCellStyle(customerCellStyle);
			
			// "订单号",
			nCell = nRow.createCell(cellNo++);
			nCell.setCellValue(cp.getOrderNo());
			nCell.setCellStyle(orderNoCellStyle);
			
			// "货号", 
			nCell = nRow.createCell(cellNo++);
			nCell.setCellValue(cp.getProductNo());
			nCell.setCellStyle(productNoCellStyle);
			
			//"数量",
			nCell = nRow.createCell(cellNo++);
			nCell.setCellValue(cp.getCnumber());
			nCell.setCellStyle(cNumberCellStyle);
			
			
			// "工厂", 
			nCell = nRow.createCell(cellNo++);
			nCell.setCellValue(cp.getFactoryName());
			nCell.setCellStyle(factoryNameCellStyle);
			
			
			//"工厂交期", 
			nCell = nRow.createCell(cellNo++);
			nCell.setCellValue(UtilFuns.dateTimeFormat(cp.getContract().getDeliveryPeriod()));
			nCell.setCellStyle(deliveryPeriodCellStyle);
			
			//"船期", 
			nCell = nRow.createCell(cellNo++);
			nCell.setCellValue(UtilFuns.dateTimeFormat(cp.getContract().getShipTime()));
			nCell.setCellStyle(shipTimeCellStyle);
			
			//"贸易条款"
			nCell = nRow.createCell(cellNo++);
			nCell.setCellValue(cp.getContract().getTradeTerms());
			nCell.setCellStyle(tradeTermsCellStyle);
			
		}	
		

		// 8.==============================实现文件下载========================
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream(); //缓存流
		
		wb.write(baos);//将工作簿的内容全部输出到缓存
		baos.close();//刷新后，缓存中的数	据是最新的
		
		HttpServletResponse response = ServletActionContext.getResponse();//得到response对象
		DownloadUtil downloadUtil = new DownloadUtil();
		downloadUtil.download(baos, response, "出货表.xls");//文件下载
		
		return NONE;
		
	}

	public String print_old() throws Exception {
		// 1.创建工作簿
		Workbook wb = new HSSFWorkbook();
		// 2.创建工作表
		Sheet sheet = wb.createSheet();

		// 定义一些公共变量
		int rowNo = 0, cellNo = 1;
		Row nRow = null;
		Cell nCell = null;

		// 设置列宽 第一个参数为列的下标 第二个参数，列的宽度 setColumnWidth存在bug，后面要用别的方法
		sheet.setColumnWidth(0, 2 * 256);
		sheet.setColumnWidth(1, 26 * 256);
		sheet.setColumnWidth(2, 11 * 256);
		sheet.setColumnWidth(3, 29 * 256);
		sheet.setColumnWidth(4, 12 * 256);
		sheet.setColumnWidth(5, 15 * 256);
		sheet.setColumnWidth(6, 10 * 256);
		sheet.setColumnWidth(7, 10 * 256);
		sheet.setColumnWidth(8, 8 * 256);
		
		// 3.=================================大标题======================
		nRow = sheet.createRow(rowNo++);// 第一行的行对象 rowNo++就会进入第二行
		nRow.setHeightInPoints(36f);// 设置行高
		
		// 产生单元格对象
		nCell= nRow.createCell(cellNo);

		// 合并单元格
		sheet.addMergedRegion(new CellRangeAddress(0,0,1,8));  //横向合并单元格
		// 设置单元格的内容
		nCell.setCellValue(inputDate.replace("-0", "-").replace("-","年")+"月份出货表");//2015-10

		// 设置单元格的样式
		nCell.setCellStyle(this.bigTitle(wb));
		// 4.==================================小标题===========================
		// 产生小标题的行对象
		String [] titles={"客户","订单号","货号","数量","工厂","工厂交期","船期","贸易条款"};
		//产生小标题的行对象
		nRow=sheet.createRow(rowNo++);
		//设置行高
		nRow.setHeightInPoints(26.25f);
		
		for(int i =0;i<titles.length;i++){
			nCell= nRow.createCell(cellNo++);   //产生单元格对象
			nCell.setCellValue(titles[i]);      //内容
			nCell.setCellStyle(this.title(wb)); //样式
		}
		// 5.==================================内容 ==============================
		String hql = "from ContractProduct where to_char(contract.shipTime,'yyyy-MM')='"+inputDate+"'";																								// 符合船期要求）
		List<ContractProduct> lists = contractProductService.find(hql, ContractProduct.class, null);
		// 遍历货物列表
		for(ContractProduct cp:lists){
			nRow = sheet.createRow(rowNo++);//产生行对象
			nRow.setHeightInPoints(24f);    //设置行高
			
			cellNo=1;
			
			nCell = nRow.createCell(cellNo++); //产生单元格对象
			nCell.setCellValue(cp.getContract().getCustomName()); //设置单元格内容
			nCell.setCellStyle(this.text(wb));
			
			//订单号
			nCell = nRow.createCell(cellNo++);//产生单元格对象
			nCell.setCellValue(cp.getOrderNo());//单元格的内容 
			nCell.setCellStyle(this.text(wb));//设置样式
			
			//货号
			nCell = nRow.createCell(cellNo++);//产生单元格对象
			nCell.setCellValue(cp.getProductNo());//单元格的内容 
			nCell.setCellStyle(this.text(wb));//设置样式
			
			//数量
			nCell = nRow.createCell(cellNo++);//产生单元格对象
			nCell.setCellValue(cp.getCnumber());//单元格的内容 
			nCell.setCellStyle(this.text(wb));//设置样式
			
			//工厂
			nCell = nRow.createCell(cellNo++);//产生单元格对象
			nCell.setCellValue(cp.getFactoryName());//单元格的内容 
			nCell.setCellStyle(this.text(wb));//设置样式
			
			//工厂交期
			nCell = nRow.createCell(cellNo++);//产生单元格对象
			nCell.setCellValue(UtilFuns.dateTimeFormat(cp.getContract().getDeliveryPeriod()));//单元格的内容 
			nCell.setCellStyle(this.text(wb));//设置样式
			
			//船期
			nCell = nRow.createCell(cellNo++);//产生单元格对象
			nCell.setCellValue(UtilFuns.dateTimeFormat(cp.getContract().getShipTime()));//单元格的内容 
			nCell.setCellStyle(this.text(wb));//设置样式
			
			//贸易条款
			nCell = nRow.createCell(cellNo++);//产生单元格对象
			nCell.setCellValue(cp.getContract().getTradeTerms());//单元格的内容 
			nCell.setCellStyle(this.text(wb));//设置样式
			
		}

		// 6.==============================实现文件下载========================
		DownloadUtil downloadUtil = new DownloadUtil();
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream(); //缓存
		
		wb.write(baos);//将工作簿的内容全部输出到缓存
		baos.close();//刷新后，缓存中的数	据是最新的
		
		HttpServletResponse response = ServletActionContext.getResponse();//得到response对象
			
		downloadUtil.download(baos, response, "出货表.xls");//文件下载
		
		return NONE;
	}

	// 大标题的样式
	public CellStyle bigTitle(Workbook wb) {
		CellStyle style = wb.createCellStyle();
		Font font = wb.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 16);
		font.setBoldweight(Font.BOLDWEIGHT_BOLD); // 字体加粗

		style.setFont(font);

		style.setAlignment(CellStyle.ALIGN_CENTER); // 横向居中
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 纵向居中
		
		return style;
	}

	// 小标题的样式
	public CellStyle title(Workbook wb) {
		CellStyle style = wb.createCellStyle();
		Font font = wb.createFont();
		font.setFontName("黑体");
		font.setFontHeightInPoints((short) 12);

		style.setFont(font);

		style.setAlignment(CellStyle.ALIGN_CENTER); // 横向居中
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 纵向居中

		style.setBorderTop(CellStyle.BORDER_THIN); // 上细线
		style.setBorderBottom(CellStyle.BORDER_THIN); // 下细线
		style.setBorderLeft(CellStyle.BORDER_THIN); // 左细线
		style.setBorderRight(CellStyle.BORDER_THIN); // 右细线

		return style;
	}

	// 文字样式
	public CellStyle text(Workbook wb){
				CellStyle style = wb.createCellStyle();
				Font font = wb.createFont();
				font.setFontName("Times New Roman");
				font.setFontHeightInPoints((short)10);
				
				style.setFont(font);
				
				style.setAlignment(CellStyle.ALIGN_LEFT);					//横向居左
				style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);		//纵向居中
				
				style.setBorderTop(CellStyle.BORDER_THIN);					//上细线
				style.setBorderBottom(CellStyle.BORDER_THIN);				//下细线
				style.setBorderLeft(CellStyle.BORDER_THIN);					//左细线
				style.setBorderRight(CellStyle.BORDER_THIN);				//右细线
				
				return style;
	}
}