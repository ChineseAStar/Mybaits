package com.service.service;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.xingxing.model.Emp;

public class EmpService {
	
	public Workbook exportEmp(List<Emp> list) throws Exception {
		String[] excelHeader={"编号","姓名","工作","上司","入职时间","薪水","奖金","部门编号"};  //设置Excel头部  
		Workbook wb = new SXSSFWorkbook(1000);
		
		Sheet sheet = wb.createSheet("sheet1");
		Row row = sheet.createRow(0);
        CellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);  //对齐方式  
        
        //导入头部  
        for (int i = 0; i < excelHeader.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(excelHeader[i]);
            cell.setCellStyle(style);
            sheet.setColumnWidth(1, (short) 6000);  //设置列宽 
        }
		
		//导入数据
        int i = 0;
        for(Emp emp : list) {
        	row = sheet.createRow(++i);
        	row.createCell(0).setCellValue(emp.getEmpno());
        	row.createCell(1).setCellValue(emp.getEname());
        	row.createCell(2).setCellValue(emp.getJob());
        	row.createCell(3).setCellValue(emp.getMgr());
        	row.createCell(4).setCellValue(emp.getHiredate());
        	row.createCell(5).setCellValue(emp.getSal());
        	row.createCell(6).setCellValue(emp.getComm());
        	row.createCell(7).setCellValue(emp.getDeptno());
        }
        
		return wb;
	}
	
}
