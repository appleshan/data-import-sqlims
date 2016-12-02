package com.daanhealth.datax.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.daanhealth.datax.datasource.TargetDataSource;
import com.daanhealth.datax.entity.NclResult;
import com.daanhealth.datax.entity.TestItemMap;
import com.daanhealth.datax.helper.TestItemMapper;
import com.daanhealth.datax.helper.ValueTipHelper;

@Component
public class ResultInfDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @TargetDataSource(name="sqlims-db")
    public List<NclResult> listResultInf(int inputType, String testDate, String seq, String hospitalId){
    	String sql = NclSql.RESULT_INF_QUERY;
    	
        Object[] params = new Object[] { inputType, testDate, seq };
        int[] types = new int[] { Types.INTEGER, Types.VARCHAR, Types.INTEGER };
        
        return jdbcTemplate.query(sql, params, types, 
        		new ResultInfRowMapper(hospitalId));
    }
}

class ResultInfRowMapper implements RowMapper<NclResult> {
	
	private String hospitalId;

	public ResultInfRowMapper(String hospitalId) {
		super();
		this.hospitalId = hospitalId;
	}

	@Override
	public NclResult mapRow(ResultSet rs, int rowNum)
			throws SQLException {
		NclResult result = new NclResult();

		result.setHospitalId(hospitalId);
		
		result.setDisplayOrder(rs.getString("DISPLAY_ORDER"));
		
		// {{ 根据 chr_itemcode 通过 项目对照 ，找出：TEST_ID , TEST_NAME
		String hisTestCode = rs.getString("chr_itemcode");
		TestItemMap testItemMap = TestItemMapper.findNclTestItem(hisTestCode);
		if(testItemMap != null) {
			String nclTestId = testItemMap.getNclTestId();
			String nclTestName = testItemMap.getNclTestName();
			result.setTestId(nclTestId);
			result.setTestName(nclTestName);
		}
		else {
			System.out.println("没有对照的检验项目："+hisTestCode);
		}
		
		// 临时记录 sqlims 的检验项目代码，之后转换为 ncl 的检验项目代码
		result.setTestCode(hisTestCode);
		// }}
		
		// {{ 确定检验结果值
		// 0 : 数值；1 : 字符；2 : 图形
		String dataformat = rs.getString("num_dataformat");
		String result1 = rs.getString("RESULT_1"); // 数值
		String result2 = rs.getString("RESULT_2"); // 字符
		
		if(StringUtils.equals(dataformat, "0")) {
			result.setResult(result1);

			result.setRefLow(rs.getString("REF_LOW"));
			result.setRefHigh(rs.getString("REF_HIGH"));
			result.setRefRange(rs.getString("REF_RANGE"));
		}
		else if(StringUtils.equals(dataformat, "1")) {
			result.setResult(result2);

//			result.setRefLow(rs.getString("REF_LOW"));
//			result.setRefHigh(rs.getString("REF_HIGH"));
//			result.setRefRange(rs.getString("REF_RANGE"));
			
			// TODO: 当 [检验结果值] 为 [阴阳性] 的时候，参考范围取 [云康LIS] 的值
		}
		// }}
		
		result.setUnit(rs.getString("UNIT"));
		
		//result.setTip(rs.getString("TIP"));
		// {{ 转换检验结果值的高低提示
		// 1 - normal, 2 - low, 3 - high
		String tip = rs.getString("TIP");
		tip = ValueTipHelper.convertResultTip(tip);
		result.setTip(tip);
		// }}

		result.setCreateDate(rs.getString("CREATE_DATE"));
		result.setLastDate(rs.getString("LAST_DATE"));
		
		result.setCreateUserName(rs.getString("CREATE_USER_NAME"));
		
		return result;
	}
}
