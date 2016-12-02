package com.daanhealth.datax.helper;

import java.util.Calendar;

public class DateHelper {

    public static final String AGE_DATE = "日";
    public static final String AGE_DAY = "天";
    public static final String AGE_MONTH = "月";
    public static final String AGE_YEAR = "岁";
    public static final String AGE_YEAR_EN_1 = "y";
    public static final String AGE_YEAR_EN_2 = "Y";

    public static final String[] units = {AGE_YEAR, AGE_MONTH, AGE_DAY, AGE_DATE, AGE_YEAR_EN_1, AGE_YEAR_EN_2};

    public static final String DEFAULT_AGE_VALUE = "0";
    public static final String DEFAULT_AGE_UNIT = AGE_YEAR;

    /**
     * 根据生日获取年龄.
     * @author apple.shan
     * @param birthday
     * @return
     */
    public static String[] calculateAgeAndUnitByBirthday(java.util.Date birthday) {
        if( birthday == null ) {
            return new String[]{DEFAULT_AGE_VALUE, DEFAULT_AGE_UNIT};
        }

        //当天日期
        Calendar today = Calendar.getInstance();
        int yearOfToday = today.get(Calendar.YEAR);

        //某个人的生日日期
        Calendar brithday = Calendar.getInstance();
        brithday.setTime(birthday);

        int age = 0;
        String unit = null;
        int yearOfBrith = brithday.get(Calendar.YEAR);
        if(yearOfToday > yearOfBrith) {//计算年龄有几个岁.
            age = yearOfToday - yearOfBrith;
            unit = AGE_YEAR;
        }
        else if(yearOfToday == yearOfBrith) {
            int monthOfToday = today.get(Calendar.MONTH)+1;
            int monthOfBrith = brithday.get(Calendar.MONTH)+1;
            if(monthOfToday > monthOfBrith) {//计算年龄有几个月.
                age = monthOfToday - monthOfBrith;
                unit = AGE_MONTH;
            }
            else if(monthOfToday == monthOfBrith) {//计算年龄有几天.
                int dayOfToday = today.get(Calendar.DAY_OF_MONTH);
                int dayOfBrith = brithday.get(Calendar.DAY_OF_MONTH);
                if(dayOfToday > dayOfBrith) {
                    age = dayOfToday - dayOfBrith;
                    unit = AGE_DAY;
                }
            }
        }

        String[] result = {Integer.toString(age), unit};
        return result;
    }

}
