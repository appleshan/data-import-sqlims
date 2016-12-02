package com.daanhealth.datax.entity;

import java.sql.Timestamp;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class NclReport {
	private String id;
	private String idContent;
	private String idType;
	private String seq;
	
	private String instrumentId;

	private String specimenTypeId;
	private String specimenTypeName;
	private String specimenTypeCode;
	
	private String collectDate;
	private String receiveDate;
	private String testDate;
	private String checkDate;
	private String lastDate;

	private String testUser;
	private String checkUser;

	private String clinicalRemark;
	private String internalCommet;
	private String reportCommet;

	private String department;
	private String doctor;

	private String patientType;
	private String patientName;
	private int gender;
	private int age;
	private int ageUnit;
	private int calculateAge;
	private String bed;

	private Timestamp createDate;
	private String createUserId;
	private String createUserName;
	private String hospitalId;

	private String state;
	private int printNumber;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdContent() {
		return idContent;
	}

	public void setIdContent(String id) {
		this.idContent = id;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}
	
	public String getInstrumentId() {
		return instrumentId;
	}

	public void setInstrumentId(String instrumentId) {
		this.instrumentId = instrumentId;
	}

	public String getSpecimenTypeId() {
		return specimenTypeId;
	}

	public void setSpecimenTypeId(String specimenTypeId) {
		this.specimenTypeId = specimenTypeId;
	}

	public String getSpecimenTypeName() {
		return specimenTypeName;
	}

	public void setSpecimenTypeName(String specimenTypeName) {
		this.specimenTypeName = specimenTypeName;
	}

	public String getSpecimenTypeCode() {
		return specimenTypeCode;
	}

	public void setSpecimenTypeCode(String specimenTypeCode) {
		this.specimenTypeCode = specimenTypeCode;
	}

	public String getCollectDate() {
		return collectDate;
	}

	public void setCollectDate(String collectDate) {
		this.collectDate = collectDate;
	}

	public String getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(String receiveDate) {
		this.receiveDate = receiveDate;
	}

	public String getTestDate() {
		return testDate;
	}

	public void setTestDate(String testDate) {
		this.testDate = testDate;
	}

	public String getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}

	public String getLastDate() {
		return lastDate;
	}

	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}

	public String getTestUser() {
		return testUser;
	}

	public void setTestUser(String testUser) {
		this.testUser = testUser;
	}

	public String getCheckUser() {
		return checkUser;
	}

	public void setCheckUser(String checkUser) {
		this.checkUser = checkUser;
	}

	public String getClinicalRemark() {
		return clinicalRemark;
	}

	public void setClinicalRemark(String clinicalRemark) {
		this.clinicalRemark = clinicalRemark;
	}

	public String getInternalCommet() {
		return internalCommet;
	}

	public void setInternalCommet(String internalCommet) {
		this.internalCommet = internalCommet;
	}

	public String getReportCommet() {
		return reportCommet;
	}

	public void setReportCommet(String reportCommet) {
		this.reportCommet = reportCommet;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}

	public String getPatientType() {
		return patientType;
	}

	public void setPatientType(String patientType) {
		this.patientType = patientType;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAgeUnit() {
		return ageUnit;
	}

	public void setAgeUnit(int ageUnit) {
		this.ageUnit = ageUnit;
	}

	public int getCalculateAge() {
		return calculateAge;
	}

	public void setCalculateAge(int calculateAge) {
		this.calculateAge = calculateAge;
	}

	public String getBed() {
		return bed;
	}

	public void setBed(String bed) {
		this.bed = bed;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getPrintNumber() {
		return printNumber;
	}

	public void setPrintNumber(int printNumber) {
		this.printNumber = printNumber;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE, true, true);
	}
}
