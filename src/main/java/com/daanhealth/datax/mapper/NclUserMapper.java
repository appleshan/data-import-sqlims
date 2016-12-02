package com.daanhealth.datax.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.daanhealth.datax.entity.NclUser;
import com.daanhealth.datax.mybatis.MyMapper;

/**
 * DictionaryMapper，映射SQL语句的接口，无逻辑实现
 *
 * @author appleshan
 * @create 2016年11月28日
 *
 */
@Mapper
public interface NclUserMapper extends MyMapper<NclUser>  {

	// 注解 @TargetDataSource 不可以在这里使用
	@Insert("INSERT into par_user("
			+ "id,active,user_code,user_name,remark,"
			+ "create_date,create_user_id,create_user_name,"
			+ "hospital_id,department_id) "
			+ "VALUES(#{id}, #{active}, #{userCode}, #{userName}, #{remark}, "
			+ "#{createDate}, #{createUserId}, #{createUserName}, "
			+ "#{hospitalId}, #{departmentId})")
	int add(NclUser doctor);

	@Results({
        @Result(property = "id", column = "id"),
      })
	@Select("SELECT id FROM par_user u "
			+ "WHERE u.hospital_id = #{hospitalId} "
			//+ "and d.department_id = #{departmentId} "
			+ "and u.user_name = #{userName} ")
	NclUser find(
			@Param("hospitalId")String hospitalId,
			//@Param("departmentId")String departmentId,
			@Param("userName")String userName);

}
