package com.daanhealth.datax.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daanhealth.datax.dao.NclDoctorDao;
import com.daanhealth.datax.entity.NclDoctor;

/**
 * 
 * @author appleshan
 * @create 2016年11月25日
 *
 */
@Service
public class NclDoctorService {

	@Autowired
    private NclDoctorDao doctorDao;

    public String addDoctor(String hospitalId, String departmentId, String doctorName) {
    	Timestamp createDate = new Timestamp(Calendar.getInstance().getTimeInMillis());

		String uuid = UUID.randomUUID().toString();
		String doctorId = StringUtils.remove(uuid, '-');

    	NclDoctor doctor = new NclDoctor();

    	doctor.setId(doctorId);
    	doctor.setDoctorName(doctorName);
    	doctor.setActive(true);
    	doctor.setDoctorCode(doctorName);
		// doctor.setDisplayOrder(99);
    	doctor.setCreateDate(createDate);
    	doctor.setCreateUserId("8a8a84ba5689947f01568cb7be350d9e");
    	doctor.setCreateUserName("系统管理员");
    	doctor.setHospitalId(hospitalId);
    	doctor.setRemark("MS Access Data Import");
    	
    	doctor.setDepartmentId(departmentId);
		
//		System.out.println("doctor: "+doctor);
		int addresult = doctorDao.addDoctor(doctor);
		
		if(addresult == 1) {
			System.out.println("---NclDoctor Data saved---");
			return doctorId;
		}
		return StringUtils.EMPTY;
    }
    
	public NclDoctor findDoctor(String hospitalId, /*String departmentId,*/ String name) {
		return doctorDao.findDoctor(hospitalId, /*departmentId,*/ name);
	}
}
