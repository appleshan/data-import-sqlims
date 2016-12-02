package com.daanhealth.datax.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daanhealth.datax.datasource.TargetDataSource;
import com.daanhealth.datax.entity.NclDepartment;
import com.daanhealth.datax.mapper.NclDepartmentMapper;

@Component
public class NclDepartmentDao {
	@Autowired
	private NclDepartmentMapper departmentMapper;

	@TargetDataSource(name="ncl-db")
	public int addDepartment(NclDepartment department) {
		return departmentMapper.add(department);
	}
	
	@TargetDataSource(name = "ncl-db")
	public NclDepartment findDepartment(String hospitalId, String name) {
		return departmentMapper.find(hospitalId, name);
	}
}
