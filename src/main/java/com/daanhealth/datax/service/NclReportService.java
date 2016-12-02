package com.daanhealth.datax.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daanhealth.datax.dao.NclReportDao;
import com.daanhealth.datax.dao.NclResultDao;
import com.daanhealth.datax.entity.NclReport;
import com.daanhealth.datax.entity.NclResult;
import com.daanhealth.datax.jasypt.BeanUtils;

/**
 * 
 * @author appleshan
 * @create 2016年11月24日
 *
 */
@Service
public class NclReportService {

	@Autowired
    private NclReportDao reportDao;
	
	@Autowired
    private NclResultDao resultDao;
	
	public String addReport(NclReport report, List<NclResult> results) {
		Timestamp createDate = new Timestamp(Calendar.getInstance().getTimeInMillis());
		
		String uuid = UUID.randomUUID().toString();
		String reportId = StringUtils.remove(uuid, '-');
		report.setId(reportId);
		
		report.setCreateDate(createDate);
		report.setCreateUserId("8a8a84ba5689947f01568cb7be350d9e");
		report.setCreateUserName("系统管理员");
		
		// 加密 患者姓名
		String patientName = BeanUtils.encrypt( report.getPatientName() );
		report.setPatientName(patientName);
		
		// PatientType
		report.setPatientType("8a8a84ba5689947f01568cb60b9a08da");

//		System.out.println("report: "+report);
		//
		System.out.println(">>>>START: 3.1、保存检验报告信息<<<<");
		int addresult = reportDao.addReport(report);
		if(addresult == 1) System.out.println("---Report data saved---");
		
		// test
		if(addresult == 1) {
			try {
				System.out.println(">>>>START: 3.2、保存检验结果值<<<<");
				addresult = resultDao.addResult(reportId, results);
				if(addresult == 1) System.out.println("---Result data saved---");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(addresult == 1) {
				return reportId;
			}
		}
		return StringUtils.EMPTY;
	}
}
