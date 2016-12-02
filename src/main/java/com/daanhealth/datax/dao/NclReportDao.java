package com.daanhealth.datax.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daanhealth.datax.datasource.TargetDataSource;
import com.daanhealth.datax.entity.NclReport;
import com.daanhealth.datax.mapper.NclReportMapper;

@Component
public class NclReportDao {
	@Autowired
    private NclReportMapper reportMapper;
	
	@TargetDataSource(name="ncl-db")
	public int addReport(NclReport report) {
		return reportMapper.add(report);
	}
	
}
