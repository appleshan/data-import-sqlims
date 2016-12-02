package com.daanhealth.datax.dao;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daanhealth.datax.datasource.TargetDataSource;
import com.daanhealth.datax.entity.NclResult;
import com.daanhealth.datax.mapper.NclResultMapper;

@Component
public class NclResultDao {
	@Autowired
    private NclResultMapper resultMapper;
	
	@TargetDataSource(name="ncl-db")
	public int addResult(String reportId, List<NclResult> results) throws Exception {
		for (NclResult result : results) {
			String uuid = UUID.randomUUID().toString();
			String resultId = StringUtils.remove(uuid, '-');
			
			result.setId(resultId);
			result.setReportId(reportId);// set reportId
			
			int addresult = resultMapper.add(result);
			if(addresult != 1) {
				throw new Exception("Cannot create record.");
			}
		}
		return 1;
	}
	
}
