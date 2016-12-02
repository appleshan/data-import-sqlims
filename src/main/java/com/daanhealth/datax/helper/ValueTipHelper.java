package com.daanhealth.datax.helper;

import org.apache.commons.lang3.StringUtils;

public class ValueTipHelper {

    private static final String HIS_LOW = "2";
    private static final String HIS_HIGHT = "3";
    private static final String HIS_NORMAL = "1";
    
    //↓ : L; ↑ : H; ↓★ : L; ↑★ : H; null : N
    private static final String LOW = "↓";
    private static final String HIGHT = "↑";
//    private static final String LOW_STAR = "↓★";
//    private static final String HIGHT_STAR = "↑★";
    private static final String NORMAL = StringUtils.EMPTY;
    
    public static String convertResultTip(String hightLowTip) {
        String tip = null;
        switch (hightLowTip) {
		case HIS_LOW:
			tip = LOW;
			break;
		case HIS_HIGHT:
			tip = HIGHT;
			break;
		case HIS_NORMAL:
			tip = NORMAL;
			break;
		default:
			tip = StringUtils.EMPTY;
			break;
		}
        return tip;
    }
}
