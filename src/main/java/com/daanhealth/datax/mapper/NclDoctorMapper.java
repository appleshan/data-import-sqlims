package com.daanhealth.datax.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.daanhealth.datax.entity.NclDoctor;
import com.daanhealth.datax.mybatis.MyMapper;

/**
 * DictionaryMapper，映射SQL语句的接口，无逻辑实现
 *
 * @author appleshan
 * @create 2016年11月25日
 *
 */
@Mapper
public interface NclDoctorMapper extends MyMapper<NclDoctor>  {

	// 注解 @TargetDataSource 不可以在这里使用
	@Insert("INSERT into par_bill_doctor("
			+ "id,active,doctor_code,doctor_name,remark,"
			+ "create_date,create_user_id,create_user_name,"
			+ "hospital_id,department_id) "
			+ "VALUES(#{id}, #{active}, #{doctorCode}, #{doctorName}, #{remark}, "
			+ "#{createDate}, #{createUserId}, #{createUserName}, "
			+ "#{hospitalId}, #{departmentId})")
	int add(NclDoctor doctor);

	@Results({
        @Result(property = "id", column = "id"),
      })
	@Select("SELECT id FROM par_bill_doctor d "
			+ "WHERE d.hospital_id = #{hospitalId} "
			//+ "and d.department_id = #{departmentId} "
			+ "and d.doctor_name = #{doctorName} ")
	NclDoctor find(
			@Param("hospitalId")String hospitalId,
			//@Param("departmentId")String departmentId,
			@Param("doctorName")String doctorName);

}
