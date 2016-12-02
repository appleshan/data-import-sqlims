package com.daanhealth.datax.dao;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NclSql {
	private static Logger logger = LoggerFactory.getLogger(NclSql.class);

    private static ResourceBundle parametres;
    static {
        try {
            parametres = ResourceBundle.getBundle("mysql-queries");
        } catch (MissingResourceException e) {
            logger.error("the file mysql-queries.properties not found");
        }
    }

    public static String REPORT_INF_DATE_LIST = parametres.getString("REPORT_INF_DATE_LIST");
    public static String REPORT_INF_ID_LIST = parametres.getString("REPORT_INF_ID_LIST");
    public static String REPORT_INF_QUERY = parametres.getString("REPORT_INF_QUERY");
    public static String RESULT_INF_QUERY = parametres.getString("RESULT_INF_QUERY");
}
