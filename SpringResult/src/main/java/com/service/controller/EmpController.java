package com.service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.service.service.EmpService;
import com.xingxing.mapper.EmpMapper;
import com.xingxing.model.Emp;

@Controller
@RequestMapping("/jsp")
public class EmpController {

	@Autowired
	private EmpMapper empMapper;

	private List<Emp> empList = null;
	
	private List<String> files = null;
	
	String path = this.getClass().getClassLoader().getResource("../../files").getPath();
	
	public void filesLoad() {
		files = new ArrayList<String>();
		File file = new File(path);
		File[] fs = file.listFiles();
		for(int i = 0; i < fs.length; i++) {
			files.add(fs[i].getName());
		}
	}
	
	@RequestMapping("/Emp.do")
	public String getList(Map<String, List> map){
		empList = empMapper.getList();
		map.put("list",empList);
		filesLoad();
		map.put("files", files);
		return "Emp";
	}
	@RequestMapping("/partEmp.do")
	public String getPart(Map<String, List> map,@RequestParam(value="sal")String sal) {
		empList = empMapper.getPart(sal);
		map.put("list", empList);
		filesLoad();
		map.put("files", files);
		return "Emp";
	}
	
	@RequestMapping("/downEmp.do")
	public String printEmp(Map<String, List> map,HttpServletResponse response,HttpServletRequest request) throws Exception {
		Workbook wb = new EmpService().exportEmp(empList);
		String fileName = path + "雇员表"+UUID.randomUUID()+".xlsx";
		FileOutputStream fout = null;
		try {
		    fout = new FileOutputStream(fileName);
		    wb.write(fout);
		} catch (Exception e)  {
		    e.printStackTrace();
		} finally {
		    wb.close();
		    fout.close(); 
			
		}
		
		map.put("list", empList);
		filesLoad();
		map.put("files", files);
		return "Emp";
	}
	
	@RequestMapping("/download")
	public ResponseEntity<byte[]> download(HttpServletRequest request,
			@RequestParam("filename") String filename,
			Model model) throws IOException{
		String path = request.getServletContext().getRealPath("/files/");
		File file = new File(path+File.separator+filename);
		HttpHeaders headers = new HttpHeaders();
		String downloadFileName = new String(filename.getBytes("utf-8"),"iso-8859-1");
		headers.setContentDispositionFormData("attachment", downloadFileName);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
				headers, HttpStatus.CREATED);
	}
	
	
}
