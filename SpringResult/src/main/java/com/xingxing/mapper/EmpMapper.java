package com.xingxing.mapper;

import java.util.List;

import com.xingxing.model.Emp;

public interface EmpMapper {

	List<Emp> getList();

	List<Emp> getPart(String sal);

	
	
}
