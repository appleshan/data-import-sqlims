package com.daanhealth.datax.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class NclDictionary implements Serializable {

	private static final long serialVersionUID = -7261547954371650665L;
	
	public static final String TYPECODE_PATIENT_TYPE  = "patient_type"; 	//病人类型
	public static final String TYPECODE_MEDICAL_GROUP = "MedicalGroup"; 	//医学专业组
	public static final String TYPECODE_LIBRARY_GROUP = "LibraryGroup"; 	//实验室专业组
	public static final String TYPECODE_INSTRUMENT_GROUP = "instrumentGroup"; 	//仪器专业组
	public static final String TYPECODE_SPECIMEN_TYPE = "StandCatalog"; 	//标本类型
	public static final String TYPECODE_REFER_TYPE = "RefMethod"; 			//参考值方式
	public static final String TYPECODE_UNIT_TYPE = "Unit"; 				//单位
	public static final String TYPECODE_DETAIL_TYPE = "ResultDetailType"; 	//结果输入类型/结果明细类型
	public static final String TYPECODE_TIP_TYPE = "ResultTypeTip"; 		//结果提示类型
	public static final String TYPECODE_CHECKMETHOD_TYPE = "TestMethod"; 	//检验方法
	public static final String TYPECODE_INSTRUMENT_TYPE = "instrumentType"; 	//仪器类别
	public static final String TYPECODE_RETURNTYPE = "specimenReturnType";// 标本退回类型
	
    public static final String TYPECODE_FGYZ = "fgyz"; 	//分管原则
	
	private String id;
	
	private String code;

	private String name;

	private String typeCode;

	private String typeName;

	private String fastCode;

	private String englishName;

	private String remark;

	private Integer displayOrder;

	private Boolean active;

	private Timestamp createDate;
	
	private String createUserId;
	
	private String createUserName;
	
	private String hospitalId;
	
	public NclDictionary() {}
	
	public NclDictionary(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getFastCode() {
		return fastCode;
	}

	public void setFastCode(String fastCode) {
		this.fastCode = fastCode;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE, true, true);
	}
}
