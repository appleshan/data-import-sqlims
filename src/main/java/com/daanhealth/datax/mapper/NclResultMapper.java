package com.daanhealth.datax.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.daanhealth.datax.entity.NclResult;
import com.daanhealth.datax.mybatis.MyMapper;

/**
 * ReportMapper，映射SQL语句的接口，无逻辑实现
 *
 * @author appleshan
 * @create 2016年11月24日
 *
 */
@Mapper
public interface NclResultMapper extends MyMapper<NclResult> {
	// 注解 @TargetDataSource 不可以在这里使用
	@Insert("INSERT into bus_test_report_detail(id,report_id,test_id,test_name,display_order,"
			+ "result,tip,ref_low,ref_high,ref_range,unit,"
			+ "create_date,last_date,create_user_name,hospital_id) "
			+ "VALUES(#{id}, #{reportId}, #{testId}, #{testName}, #{displayOrder}, "
			+ "#{result}, #{tip}, #{refLow}, #{refHigh}, #{refRange}, #{unit}, "
			+ "#{createDate}, #{lastDate}, #{createUserName}, #{hospitalId})")
	int add(NclResult result);
}
