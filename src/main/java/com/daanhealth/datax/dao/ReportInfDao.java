package com.daanhealth.datax.dao;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.daanhealth.datax.constant.AgeUnit;
import com.daanhealth.datax.constant.SexType;
import com.daanhealth.datax.datasource.TargetDataSource;
import com.daanhealth.datax.entity.NclReport;
import com.daanhealth.datax.helper.DateHelper;
import com.daanhealth.datax.helper.PatientTypeHelper;
import com.daanhealth.datax.helper.PropertyHelper;

import static org.apache.commons.lang3.StringUtils.defaultIfBlank;

@Component
public class ReportInfDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @TargetDataSource(name="sqlims-db")
    public List<String> listReportDate(){
    	String sql = NclSql.REPORT_INF_DATE_LIST;
    	
    	String beginTime = PropertyHelper.readSyncSqlimsBeginTime();
    	String endTime = PropertyHelper.readSyncSqlimsEndTime();

        Object[] params = new Object[] { beginTime, endTime };
        int[] types = new int[] { Types.VARCHAR, Types.VARCHAR };
        
        return (List<String>) jdbcTemplate.queryForList(sql,
        		params, types, String.class);
    }

    @TargetDataSource(name="sqlims-db")
    public List<String> listReportIdContent(int inputType, String inputDate){
    	String sql = NclSql.REPORT_INF_ID_LIST;

        Object[] params = new Object[] { inputType, inputDate };
        int[] types = new int[] { Types.INTEGER, Types.VARCHAR };
        
        return (List<String>) jdbcTemplate.queryForList(sql,
        		params, types, String.class);
    }

    @TargetDataSource(name="sqlims-db")
    public List<NclReport> listReportInf(String idContent, String hospitalId){
    	String sql = NclSql.REPORT_INF_QUERY;
    	
        Object[] params = new Object[] { idContent };
        int[] types = new int[] { Types.VARCHAR };
    	
        return (List<NclReport>) jdbcTemplate.query(sql,
        		params, types,
        		new ReportInfRowMapper(hospitalId));
    }

}

class ReportInfRowMapper implements RowMapper<NclReport> {

	private String hospitalId;

	public ReportInfRowMapper(String hospitalId) {
		super();
		this.hospitalId = hospitalId;
	}

    @Override
    public NclReport mapRow(ResultSet rs, int rowNum)
    		throws SQLException {
    	NclReport report = new NclReport();
    	
    	report.setHospitalId(hospitalId);
    	
    	report.setIdContent(rs.getString("ID_CONTENT"));
    	report.setIdType(rs.getString("ID_TYPE"));
    	report.setSeq(rs.getString("SEQ"));
    	
//    	report.setSpecimenTypeId(rs.getString("SPECIMEN_TYPE_ID"));
    	report.setSpecimenTypeName(rs.getString("SPECIMEN_TYPE_NAME"));
    	report.setSpecimenTypeCode(rs.getString("SPECIMEN_TYPE_CODE"));
    	
    	report.setCollectDate(rs.getString("COLLECT_DATE"));
    	report.setReceiveDate(rs.getString("RECEIVE_DATE"));
    	report.setCreateDate(rs.getTimestamp("CREATE_DATE"));
    	report.setTestDate(rs.getString("TEST_DATE"));
    	report.setCheckDate(rs.getString("CHECK_DATE"));
    	report.setLastDate(rs.getString("LAST_DATE"));
    	
    	report.setTestUser(rs.getString("TEST_USER"));
    	report.setCheckUser(rs.getString("CHECK_USER"));
    	
    	report.setClinicalRemark(rs.getString("CLINICAL_REMARK"));
    	report.setInternalCommet(rs.getString("INTERNAL_COMMET"));
    	report.setReportCommet(rs.getString("REPORT_COMMET"));
    	
    	report.setDepartment(rs.getString("DEPARTMENT"));
    	report.setDoctor(rs.getString("DOCTOR"));

    	String type = rs.getString("PATIENT_TYPE");
    	String ptype = PatientTypeHelper.findPatientType(type);
    	report.setPatientType(ptype);
    	
    	report.setPatientName(rs.getString("PATIENT_NAME"));
    	
    	// 转换性别的代码
    	String sexValue = rs.getString("SEX");
    	report.setGender(SexType.getIndex(sexValue));
    	
    	// {{ age && ageUnit
    	// 根据患者生日[dat_birth]，计算出 AGE、AGE_UNIT、CALCULATE_AGE.
    	String birthdayString = defaultIfBlank(rs.getString("BIRTHDAY"), "1900-01-01");
    	Date birthday = Date.valueOf(birthdayString);
    	
    	String[] ageResult = DateHelper.calculateAgeAndUnitByBirthday(birthday);
        int age = Integer.valueOf( ageResult[0] );
        String ageUnit = ageResult[1];
    	
    	report.setAge(age);
    	report.setAgeUnit(AgeUnit.getIndex(ageUnit));
    	report.setCalculateAge(age);
    	// }}
    	
    	report.setBed(rs.getString("BED"));
    	
    	// {{
    	// A.当 [chr_prinstat] 为：1 [已打印]，SPECIMEN_SEQ_STATE = 103，PRINTNUM = 1
    	String prinstat = rs.getString("chr_prinstat");
    	String state = rs.getString("chr_state");
    	if(StringUtils.equals("1", prinstat)) {
    		report.setState("103");
    		report.setPrintNumber(1);
    	}
    	// B.当 [chr_state] 为：1 [经过检查]，SPECIMEN_SEQ_STATE = 102
    	else if(StringUtils.equals("1", state)) {
    		report.setState("102");
    	}
    	// }}
    	
        return report;
    }

}
