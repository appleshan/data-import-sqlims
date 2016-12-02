package com.daanhealth.datax.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.daanhealth.datax.entity.NclDepartment;
import com.daanhealth.datax.mybatis.MyMapper;

/**
 * DictionaryMapper，映射SQL语句的接口，无逻辑实现
 *
 * @author appleshan
 * @create 2016年11月25日
 *
 */
@Mapper
public interface NclDepartmentMapper extends MyMapper<NclDepartment>  {

	// 注解 @TargetDataSource 不可以在这里使用
	@Insert("INSERT into par_department("
			+ "id,active,department_code,department_name,remark,create_date,create_user_id,create_user_name,hospital_id) "
			+ "VALUES(#{id}, #{active}, #{code}, #{name}, #{remark}, #{createDate}, #{createUserId}, #{createUserName}, #{hospitalId})")
	int add(NclDepartment department);

	@Results({
        @Result(property = "id", column = "id"),
      })
	@Select("SELECT id FROM par_department d "
			+ "WHERE d.hospital_id = #{hospitalId} "
			+ "and d.department_name = #{name}")
	NclDepartment find(
			@Param("hospitalId")String hospitalId,
			@Param("name")String name);
}
