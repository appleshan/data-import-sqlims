package com.daanhealth.datax.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.daanhealth.datax.entity.NclReport;
import com.daanhealth.datax.mybatis.MyMapper;

/**
 * ReportMapper，映射SQL语句的接口，无逻辑实现
 *
 * @author appleshan
 * @create 2016年11月18日
 *
 */
@Mapper
public interface NclReportMapper extends MyMapper<NclReport> {

	// 注解 @TargetDataSource 不可以在这里使用
	@Insert("INSERT into bus_test_report("
			+ "id,specimen_seq_state,id_type,id_content,seq,instrument_id,specimen_type_id,"
			+ "departament,doctor,patient_type,patient_name,sex,age,age_unit,calculate_age,bed,"
			+ "collect_date,receive_date,create_date,test_date,check_date,last_date,"
			+ "test_user,check_user,clinical_remark,internal_commet,report_commet,printnum,"
			+ "create_user_id,create_user_name,hospital_id) "
			+ "VALUES(#{id}, #{state}, #{idType}, #{idContent}, #{seq}, #{instrumentId}, #{specimenTypeId}, "
			+ "#{department}, #{doctor}, #{patientType}, #{patientName}, #{gender}, #{age}, #{ageUnit}, #{calculateAge}, #{bed}, "
			+ "#{collectDate}, #{receiveDate}, #{createDate}, #{testDate}, #{checkDate}, #{lastDate}, "
			+ "#{testUser}, #{checkUser}, #{clinicalRemark}, #{internalCommet}, #{reportCommet}, #{printNumber}, "
			+ "#{createUserId}, #{createUserName}, #{hospitalId})")
	int add(NclReport report);

}
