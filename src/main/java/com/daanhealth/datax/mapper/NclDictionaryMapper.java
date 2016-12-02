package com.daanhealth.datax.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.daanhealth.datax.entity.NclDictionary;
import com.daanhealth.datax.mybatis.MyMapper;

/**
 * DictionaryMapper，映射SQL语句的接口，无逻辑实现
 *
 * @author appleshan
 * @create 2016年11月18日
 *
 */
@Mapper
public interface NclDictionaryMapper extends MyMapper<NclDictionary> {

	// 注解 @TargetDataSource 不可以在这里使用
	@Insert("INSERT into par_dictionary("
			+ "id,active,code,name,typecode,typename,remark,create_date,create_user_id,create_user_name,hospital_id) "
			+ "VALUES(#{id}, #{active}, #{code}, #{name}, #{typeCode}, #{typeName}, #{remark}, #{createDate}, #{createUserId}, #{createUserName}, #{hospitalId})")
	int add(NclDictionary dict);

	@Results({
        @Result(property = "id", column = "id"),
      })
	@Select("SELECT id FROM par_dictionary d "
			+ "WHERE d.hospital_id = #{hospitalId} "
			+ "and d.typecode = #{typeCode} and d.code = #{code} and d.name = #{name}")
	NclDictionary find(
			@Param("hospitalId")String hospitalId,
			@Param("typeCode")String typeCode,
			@Param("code")String code,
			@Param("name")String name);

}
