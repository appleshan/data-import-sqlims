package com.daanhealth.datax.jasypt;

import com.daanhealth.datax.helper.PropertyHelper;

public class Configuration {
    public static final String ALGORITHM = PropertyHelper.readJasyptAlgorithm();
    public static final String JASYPT_PASSWORD = PropertyHelper.readJasyptPassword();
}
