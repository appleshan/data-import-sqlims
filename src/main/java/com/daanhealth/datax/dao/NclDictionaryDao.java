package com.daanhealth.datax.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daanhealth.datax.datasource.TargetDataSource;
import com.daanhealth.datax.entity.NclDictionary;
import com.daanhealth.datax.mapper.NclDictionaryMapper;

@Component
public class NclDictionaryDao {
	@Autowired
    private NclDictionaryMapper dictMapper;
	
	@TargetDataSource(name="ncl-db")
	public int addDict(NclDictionary dict) {
		return dictMapper.add(dict);
	}
	
	@TargetDataSource(name="ncl-db")
	public NclDictionary findDict(String hospitalId, String typeCode, String code, String name) {
		return dictMapper.find(hospitalId, typeCode, code, name);
	}
}
