package com.daanhealth.datax.entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TestItemMap {
    private String hisTestCode;
    private String hisTestName;
    private String nclTestId;
    private String nclTestCode;
    private String nclFastCode;
    private String nclEnglishName;
    private String nclTestName;
    
	public String getHisTestCode() {
		return hisTestCode;
	}
	public void setHisTestCode(String hisTestCode) {
		this.hisTestCode = hisTestCode;
	}
	public String getHisTestName() {
		return hisTestName;
	}
	public void setHisTestName(String hisTestName) {
		this.hisTestName = hisTestName;
	}
	public String getNclTestId() {
		return nclTestId;
	}
	public void setNclTestId(String nclTestId) {
		this.nclTestId = nclTestId;
	}
	public String getNclTestCode() {
		return nclTestCode;
	}
	public void setNclTestCode(String nclTestCode) {
		this.nclTestCode = nclTestCode;
	}
	public String getNclFastCode() {
		return nclFastCode;
	}
	public void setNclFastCode(String nclFastCode) {
		this.nclFastCode = nclFastCode;
	}
	public String getNclEnglishName() {
		return nclEnglishName;
	}
	public void setNclEnglishName(String nclEnglishName) {
		this.nclEnglishName = nclEnglishName;
	}
	public String getNclTestName() {
		return nclTestName;
	}
	public void setNclTestName(String nclTestName) {
		this.nclTestName = nclTestName;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE, true, true);
	}
}
