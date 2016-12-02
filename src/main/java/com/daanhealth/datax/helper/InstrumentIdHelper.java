package com.daanhealth.datax.helper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class InstrumentIdHelper {

	public static final Map<String, String> instrumentInfoMap = new HashMap<String, String>();
	
	static {
		instrumentInfoMap.put("手工项目", "8a8a84ba5689947f01568ce75e7b1a9c");
		instrumentInfoMap.put("生化仪-AU400", "8a8a84ba5689947f01568cbed9be0f0d");
	}
	
	// 属于[手工项目]仪器的检验项目
	private static final List<String> testItemCodes = Arrays.asList(
			"BG", "EB", "HIV", "KET", "MP", "RH",
			"HBcAb", "HBeAb", "HBeAg", "HBsAb", "HBsAg",
			"HCG"
			);
	
	public static String findInstrumentId(String testItemCode) {
		for (String code : testItemCodes) {
			if(StringUtils.equalsIgnoreCase(code, testItemCode)) {
				return instrumentInfoMap.get("手工项目");
			}
		}
		return instrumentInfoMap.get("生化仪-AU400");
	}
}
