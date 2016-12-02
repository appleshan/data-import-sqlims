package com.daanhealth.datax.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daanhealth.datax.dao.NclUserDao;
import com.daanhealth.datax.entity.NclUser;

/**
 * 
 * @author appleshan
 * @create 2016年11月28日
 *
 */
@Service
public class NclUserService {

	@Autowired
    private NclUserDao userDao;

    public String addUser(String hospitalId, String departmentId, String userName) {
    	Timestamp createDate = new Timestamp(Calendar.getInstance().getTimeInMillis());

		String uuid = UUID.randomUUID().toString();
		String userId = StringUtils.remove(uuid, '-');

    	NclUser user = new NclUser();

    	user.setId(userId);
    	user.setUserName(userName);
    	user.setActive(true);
    	user.setUserCode(userName);
    	user.setCreateDate(createDate);
    	user.setCreateUserId("8a8a84ba5689947f01568cb7be350d9e");
    	user.setCreateUserName("系统管理员");
    	user.setHospitalId(hospitalId);
    	user.setRemark("MS Access Data Import");
    	
    	user.setDepartmentId(departmentId);
		
//		System.out.println("doctor: "+doctor);
		int addresult = userDao.addUser(user);
		
		if(addresult == 1) {
			System.out.println("---NclUser Data saved---");
			return userId;
		}
		return StringUtils.EMPTY;
    }
    
	public NclUser findUser(String hospitalId, /*String departmentId,*/ String userName) {
		return userDao.findUser(hospitalId, /*departmentId,*/ userName);
	}
}
