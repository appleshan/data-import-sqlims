package com.daanhealth.datax.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.daanhealth.datax.dao.ReportInfDao;
import com.daanhealth.datax.dao.ResultInfDao;
import com.daanhealth.datax.entity.NclDepartment;
import com.daanhealth.datax.entity.NclDictionary;
import com.daanhealth.datax.entity.NclDoctor;
import com.daanhealth.datax.entity.NclReport;
import com.daanhealth.datax.entity.NclResult;
import com.daanhealth.datax.entity.NclUser;
import com.daanhealth.datax.helper.InstrumentIdHelper;
import com.daanhealth.datax.helper.PropertyHelper;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * 
 * @author appleshan
 * @create 2016年11月18日
 *
 */
@Service
public class MainService {
	private static final Logger logger =
			LoggerFactory.getLogger(MainService.class);

	// 查询仪器传输的检验结果值
	private static final int inputTypeByAuto = 0;
	
	// 查询手工填写的检验结果值
	private static final int inputTypeByHand = 1;

	@Autowired
    private ReportInfDao reportInfDao;
	
	@Autowired
    private ResultInfDao resultInfDao;

	@Autowired
    private NclDictionaryService dictService;

	@Autowired
	private NclDoctorService doctorService;
	
	@Autowired
	private NclUserService userService;
	
	@Autowired
    private NclDepartmentService departmentService;
	
	@Autowired
    private NclReportService reportService;

	private String hospitalId = PropertyHelper.readNclHospitalId();
	
	private static final int totalDataRow = 281666;
	
	public void syncLisReportByAuto() {
		this.syncLisReport(inputTypeByAuto);
	}
	
	public void syncLisReportByHand() {
		this.syncLisReport(inputTypeByHand);
	}
	
	private void syncLisReport(int inputType) {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>START: 服务启动执行，执行数据同步操作<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		
		int index = 0;
		List<String> reportDates = reportInfDao.listReportDate();
		for (String reportDate : reportDates) {
			// 1、查询 sqlims 数据库中，[检验报告的条码号]的列表
//			System.out.println(">>>>>>START: 1、查询 sqlims 数据库中，[检验报告的条码号]的列表<<<<<<");
			List<String> reportIdContents = reportInfDao.listReportIdContent(inputType, reportDate);
//			System.out.println(reportDate+">>列表中[检验报告的条码号]的数量：" + reportIdContents.size());
			
			// 2、开始逐条处理[检验报告]
//			System.out.println(">>>>>>START: 2、开始逐条处理[检验报告]<<<<<<");
			for (String idContent : reportIdContents) {
				if(StringUtils.isBlank(idContent)) {
					logger.info("idContent isBlank: "+idContent);
					continue;
				}
				
				// 2.1、根据[检验报告的条码号]，查询检验报告中的患者信息
//				System.out.println(">>>>START: 2.1、根据[检验报告的条码号]，查询检验报告中的患者信息<<<<");
				List<NclReport> reports = reportInfDao.listReportInf(idContent, hospitalId);
//				System.out.println("列表中[检验报告]的数量：" + reports.size());
//				logger.info("idContent="+idContent+" |reports="+reports);
				
				for (NclReport report : reports) {
					// sqlims db 中，report_inf 与 result_inf 的关联关系是 检验时间 [dat_inputdate] 和 检验顺序号 [seq].
					String testDate = report.getTestDate();
					String seq = report.getSeq();

					// 2.2、根据[检验报告的条码号]，查询检验报告中的检验结果信息
//					System.out.println(">>>>START: 2.2、根据[检验报告的条码号]，查询检验报告中的检验结果信息<<<<");
					List<NclResult> results = resultInfDao.listResultInf(inputType, testDate, seq, hospitalId);
//					System.out.println("列表中[检验结果]的数量：" + results.size());
//					logger.info("results="+results);
					
					if(CollectionUtils.isEmpty(results)) {
						System.out.println("ERROR: 该检验报告 ["+idContent+"] 没有 [检验结果]");
						continue;
					}
					
					// 2.2.1、确定仪器编号
					/*
//					System.out.println(">>START: 2.2.1、确定仪器编号<<");
					String instrumentId = InstrumentIdHelper.findInstrumentId(results.get(0).getTestCode());
					report.setInstrumentId(instrumentId);
					if(StringUtils.isBlank(instrumentId)) {
						logger.error("该检验报告 ["+idContent+"] 缺少检验仪器.");
					}
					*/
					if(inputType == 0) {
						String instrumentId = InstrumentIdHelper.instrumentInfoMap.get("生化仪-AU400");
						report.setInstrumentId(instrumentId);
					}
					else if(inputType == 1) {
						String instrumentId = InstrumentIdHelper.instrumentInfoMap.get("手工项目");
						report.setInstrumentId(instrumentId);
					}
					
					// 2.2.2、确定标本类型
					String code = report.getSpecimenTypeCode();
					String name = report.getSpecimenTypeName();
					NclDictionary dict = dictService.findStandCatalogDict(hospitalId, code, name);
//					System.out.println("dict：" + dict);
					if(dict == null && isNotBlank(code) && isNotBlank(name) ) {
						System.out.println(">>START:2.2.2、确定标本类型<<");
						String specimenTypeId = dictService.addStandCatalogDictionary(hospitalId, code, name);
//						System.out.println("specimenTypeId：" + specimenTypeId);
						report.setSpecimenTypeId(specimenTypeId);
					}
					else if(dict == null && isBlank(code) && isBlank(name) ) {
						// 当都为null，不需要做什么事儿。
					}
					else if(dict != null) {
						report.setSpecimenTypeId(dict.getId());
					}
					
					// 2.2.3 确定 TEST_USER
					String testDoctorDepartmentId = "05833290becc46ad8455195d1e94c2d6";
					String testDoctorName = report.getTestUser();
					NclUser testUser = userService.findUser(hospitalId, /*testDoctorDepartmentId,*/ testDoctorName);
					if(testUser == null && isNotBlank(testDoctorName)) {
						System.out.println(">>START:2.2.3、确定检验医生<<");
						String userId = userService.addUser(hospitalId, testDoctorDepartmentId, testDoctorName);
						report.setTestUser(userId);
					}
					else if(testUser == null && isBlank(testDoctorName) ) {
						// 当都为null，不需要做什么事儿。
					}
					else if(testUser != null) {
						report.setTestUser(testUser.getId());
					}
					
					// 2.2.4 确定 CHECK_USER
					// 在 sqlims 中 num_checkerid 全部 == 0，即没有真实的值

					// 2.2.5 确定 DEPARTMENT
					String departmentId = null;
					String sendDepartmentName = report.getDepartment();
					NclDepartment sendDepartment = departmentService.findDepartment(hospitalId, sendDepartmentName);
					if(sendDepartment == null && isNotBlank(sendDepartmentName)) {
						System.out.println(">>START:2.2.3、确定送检科室<<");
						departmentId = departmentService.addDepartment(hospitalId, sendDepartmentName);
						report.setDepartment(departmentId);
					}
					else if(sendDepartment == null && isNotBlank(sendDepartmentName)) {
						// 当两个为null，不需要做什么事儿。
					}
					else if(sendDepartment != null) {
						departmentId = sendDepartment.getId();
						report.setDepartment(departmentId);
					}
					
					// 2.2.6 确定 DOCTOR
					String sendDoctorName = report.getDoctor();
					NclDoctor sendDoctor = doctorService.findDoctor(hospitalId, /*departmentId,*/ sendDoctorName);
//					System.out.println("departmentId：" + departmentId+" | sendDoctorName：" + sendDoctorName);
//					System.out.println("sendDoctor：" + sendDoctor);
					if(sendDoctor == null && isNotBlank(sendDoctorName) ) {
						System.out.println(">>START:2.2.3、确定送检医生<<");
						String doctorId = doctorService.addDoctor(hospitalId, departmentId, sendDoctorName);
						report.setDoctor(doctorId);
					}
					else if(sendDoctor == null && isBlank(sendDoctorName) ) {
						// 当都为null，不需要做什么事儿。
					}
					else if(sendDoctor != null) {
						report.setDoctor(sendDoctor.getId());
					}
					
					// 3、保存检验报告
					System.out.println(">>>>>>START: 3、保存检验报告<<<<<<");
					reportService.addReport(report, results);
					
					index++;
					System.out.println("日期: <"+reportDate+">，正在同步数据..." + index + "/" + totalDataRow );
				}
//				logger.info("instrumentId : reports="+reports);
			}
		}
		
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>END: 服务停止执行，完成数据同步操作<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	}
}
