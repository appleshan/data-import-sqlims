package com.daanhealth.datax.constant;

import org.apache.commons.lang3.StringUtils;

/**
 * AgeUnit 枚举
 * 
 * @author
 * 
 */
public enum AgeUnit implements NclEnum {
	year("岁", 0),
	month("月", 1),
	day("天", 2),
	hour("小时", 3);
	AgeUnit(String text, int index){
		this.text=text;
		this.index = index;
	}
	private String text;
	private int index;
	public String getName(){
		return this.name();		
	}
	public String getText(){
		return this.text;
	}
	public int getIndex() {
        return index;
    }
	
	/**
	 * 从 index 查询 name.
	 * @param index
	 * @return
	 */
    public static String getName(int index) {
        for (AgeUnit c : AgeUnit.values()) {
            if (c.getIndex() == index) {
                return c.text;
            }
        }
        return null;
    }

    /**
     * 从 text 查询 index.
	 * @param text
	 * @return
     */
    public static int getIndex(String text) {
    	for (AgeUnit c : AgeUnit.values()) {
            if ( StringUtils.equals(text, c.getText()) ) {
                return c.index;
            }
        }
        return year.index;
    }
    
    /**
     * 通过index获取当前枚举
     * @param index
     * @return
     */
    public static AgeUnit getAgeUnit(int index){
    	for (AgeUnit c : AgeUnit.values()) {
            if (c.getIndex() == index) {
                return c;
            }
        }
        return null;
    }
}
