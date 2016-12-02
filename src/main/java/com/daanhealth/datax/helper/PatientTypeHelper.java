package com.daanhealth.datax.helper;

import org.apache.commons.lang3.StringUtils;

public class PatientTypeHelper {
	
	/**
	 * PATIENT_TYPE 的取值范围:<br>
	 * 8a8a84ba5689947f01568cb60b9a08da  门诊  mz<br>
	 * 8a8a84ba5689947f01568cb60b9a08b0  住院  zy<br>
	 * 8a8a84ba5689947f01568cb60b8a089b  体检  tj<br>
	 * 8a8a84ba5689947f01568cb60b9a0946  急诊  jz<br>
	 */
    public static String findPatientType(String type) {
    	String ptype = null;
    	if(StringUtils.isNotBlank(type)) {
			if(StringUtils.equalsIgnoreCase(type, "tj")) {
				ptype = "8a8a84ba5689947f01568cb60b8a089b";
			}
			else if(StringUtils.equalsIgnoreCase(type, "zy")) {
				ptype = "8a8a84ba5689947f01568cb60b9a08b0";
			}
			else if(StringUtils.equalsIgnoreCase(type, "mz")) {
				ptype = "8a8a84ba5689947f01568cb60b9a08da";
			}
			else if(StringUtils.equalsIgnoreCase(type, "jz")) {
				ptype = "8a8a84ba5689947f01568cb60b9a0946";
			}
		}else{
			ptype = StringUtils.EMPTY;
		}
    	return ptype;
    }
}
