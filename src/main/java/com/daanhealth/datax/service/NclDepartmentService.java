package com.daanhealth.datax.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daanhealth.datax.dao.NclDepartmentDao;
import com.daanhealth.datax.entity.NclDepartment;

/**
 * 
 * @author appleshan
 * @create 2016年11月25日
 *
 */
@Service
public class NclDepartmentService {

	@Autowired
    private NclDepartmentDao departmentDao;

    public String addDepartment(String hospitalId, String departmentName) {
    	Timestamp createDate = new Timestamp(Calendar.getInstance().getTimeInMillis());

		String uuid = UUID.randomUUID().toString();
		String departmentId = StringUtils.remove(uuid, '-');

    	NclDepartment department = new NclDepartment();

    	department.setId(departmentId);
    	department.setName(departmentName);
    	department.setActive(true);
    	department.setCode(departmentName);
		// doctor.setDisplayOrder(99);
    	department.setCreateDate(createDate);
    	department.setCreateUserId("8a8a84ba5689947f01568cb7be350d9e");
    	department.setCreateUserName("系统管理员");
    	department.setHospitalId(hospitalId);
    	department.setRemark("MS Access Data Import");
		
//		System.out.println("department: "+department);
		int addresult = departmentDao.addDepartment(department);
		
		if(addresult == 1) {
			System.out.println("---NclDepartment Data saved---");
			return departmentId;
		}
		return StringUtils.EMPTY;
    }
    
	public NclDepartment findDepartment(String hospitalId, String name) {
		return departmentDao.findDepartment(hospitalId, name);
	}
}
