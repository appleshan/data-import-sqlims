package com.daanhealth.datax.constant;

import org.apache.commons.lang3.StringUtils;

/**
 * Sex 枚举
 *
 * @author
 *
 */
public enum SexType implements NclEnum {
    both("未知", 0),
    male("男", 1),
    female("女", 2);

    SexType(String text, int index){
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
        for (SexType c : SexType.values()) {
            if (c.getIndex() == index) {
                return c.name();
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
        for (SexType c : SexType.values()) {
            if ( StringUtils.equals(text, c.getText()) ) {
                return c.index;
            }
        }
        return both.index;
    }
    
    /**
     * 通过index获取当前枚举
     * @param index
     * @return
     */
    public static SexType getSexType(int index){
    	for (SexType c : SexType.values()) {
            if (c.getIndex() == index) {
                return c;
            }
        }
        return null;
    }

    public String toString(){
        return super.toString()+"("+index+","+text+")";
    }
}
