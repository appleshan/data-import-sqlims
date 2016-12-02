package com.daanhealth.datax.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daanhealth.datax.dao.NclDictionaryDao;
import com.daanhealth.datax.entity.NclDictionary;

/**
 * 
 * @author appleshan
 * @create 2016年11月23日
 *
 */
@Service
public class NclDictionaryService {
	private static final String TypeName = "标本类型";

	@Autowired
    private NclDictionaryDao dictDao;

    public String addStandCatalogDictionary(String hospitalId, String code, String name) {
		Timestamp createDate = new Timestamp(Calendar.getInstance().getTimeInMillis());

		String uuid = UUID.randomUUID().toString();
		String dictId = StringUtils.remove(uuid, '-');

		NclDictionary dict = new NclDictionary();
		
		dict.setId(dictId);
		dict.setName(name);
		dict.setActive(true);
		dict.setCode(code);
		// dict.setDisplayOrder(99);
		dict.setTypeCode(NclDictionary.TYPECODE_SPECIMEN_TYPE);
		dict.setTypeName(TypeName);
		dict.setCreateDate(createDate);
		dict.setCreateUserId("8a8a84ba5689947f01568cb7be350d9e");
		dict.setCreateUserName("系统管理员");
		dict.setHospitalId(hospitalId);
		dict.setRemark("MS Access Data Import");
		
//		System.out.println("dict: "+dict);
		int addresult = dictDao.addDict(dict);
		if(addresult == 1) {
			System.out.println("---NclDictionary Data saved---");
			return dictId;
		}
		return StringUtils.EMPTY;
    }
    
	public NclDictionary findStandCatalogDict(String hospitalId, String code, String name) {
		return dictDao.findDict(hospitalId, NclDictionary.TYPECODE_SPECIMEN_TYPE, code, name);
	}
}
