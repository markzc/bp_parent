package com.zc.bp.test.POI;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

public class Demo01 {
	
	@Test
	public void test01() throws Exception{
		Workbook workbook = new HSSFWorkbook();
		
		Sheet sheet = workbook.createSheet();
		
		Row row = sheet.createRow(4);
		
		Cell cell = row.createCell(3);
		
		cell.setCellValue("zhangchang");
		
		CellStyle cellStyle = workbook.createCellStyle();
		
		Font font = workbook.createFont();
		
		font.setFontHeightInPoints((short)46);
		
		font.setFontName("叶根友毛笔行书2.0版");
		
		cellStyle.setFont(font);
		
		cell.setCellStyle(cellStyle);
		
		OutputStream outputStream = new FileOutputStream("E:/a.xls");
		
		workbook.write(outputStream);
		
		outputStream.close();
	}

}
