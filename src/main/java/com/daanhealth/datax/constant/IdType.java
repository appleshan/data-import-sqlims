package com.daanhealth.datax.constant;

import org.apache.commons.lang3.StringUtils;

/**
 * Id类型枚举
 *
 * @author 肖晓翔
 *
 */
public enum IdType implements NclEnum {
    barCode_num("条码号", 0),
    patient_num("门诊号", 1),
    hospital_num("住院号", 2),
    bill_num("发票号", 3),
    exam_num("体检号", 4),
    apply_num("申请单号", 5),
    charge_num("收费单号", 6),
    test_num("病历卡号", 7);
    IdType(String text, int index){
        this.text = text;
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
        for (IdType c : IdType.values()) {
            if (c.getIndex() == index) {
                return c.name();
            }
        }
        return null;
    }

    /**
     * 从 name 查询 index.
     * @param name
     * @return
     */
    public static int getIndex(String name) {
        for (IdType c : IdType.values()) {
            if ( StringUtils.equals(name, c.getName()) ) {
                return c.index;
            }
        }
        return barCode_num.index;
    }

    /**
     * 通过index获取当前枚举
     * @param index
     * @return
     */
    public static IdType getIdType(int index){
        for (IdType c : IdType.values()) {
            if (c.getIndex() == index) {
                return c;
            }
        }
        return null;
    }
}
