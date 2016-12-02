package com.daanhealth.datax.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daanhealth.datax.datasource.TargetDataSource;
import com.daanhealth.datax.entity.NclUser;
import com.daanhealth.datax.mapper.NclUserMapper;

@Component
public class NclUserDao {
	@Autowired
	private NclUserMapper userMapper;

	@TargetDataSource(name="ncl-db")
	public int addUser(NclUser user) {
		return userMapper.add(user);
	}
	
	@TargetDataSource(name = "ncl-db")
	public NclUser findUser(String hospitalId, /*String departmentId,*/ String userName) {
		return userMapper.find(hospitalId, /*departmentId,*/ userName);
	}
}
