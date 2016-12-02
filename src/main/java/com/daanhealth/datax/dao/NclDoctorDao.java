package com.daanhealth.datax.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daanhealth.datax.datasource.TargetDataSource;
import com.daanhealth.datax.entity.NclDoctor;
import com.daanhealth.datax.mapper.NclDoctorMapper;

@Component
public class NclDoctorDao {
	@Autowired
	private NclDoctorMapper doctorMapper;

	@TargetDataSource(name="ncl-db")
	public int addDoctor(NclDoctor doctor) {
		return doctorMapper.add(doctor);
	}
	
	@TargetDataSource(name = "ncl-db")
	public NclDoctor findDoctor(String hospitalId, /*String departmentId,*/ String doctorName) {
		return doctorMapper.find(hospitalId, /*departmentId,*/ doctorName);
	}
}
