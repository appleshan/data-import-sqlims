package com.daanhealth.datax.helper;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.ReentrantLock;

public class PropertyHelper {
    protected static Logger log = LoggerFactory.getLogger(PropertyHelper.class);

    private static final String configFilePath = PathHelper.getRunPath("application.properties");
    private static final String timeFilePath =  PathHelper.getRunPath("times.properties");
    private static final ReentrantLock lock = new ReentrantLock();

    private PropertiesConfiguration instance = null;

    public PropertyHelper(String filePath) {
        lock.lock();
        try {
            if (StringUtils.isNotBlank(filePath)
                    && (
                            StringUtils.endsWith(filePath, ".properties")
                         || StringUtils.endsWith(filePath, ".ini"))) {
                instance = new PropertiesConfiguration(filePath);
            }
            instance.setReloadingStrategy(new FileChangedReloadingStrategy());// 设置文件加载策略
            // 可以重新设置 DEFAULT_REFRESH_DELAY = 5000; 默认更新延迟
            // 上次更改时间 lastModified;
            // 上次检查时间 protected long lastChecked;
            // 更新延迟 long refreshDelay = 5000L; 可以支持启动时动态更新文件
            instance.setEncoding("UTF-8");// 此类可读xml文件也可以写,具体请看方法
            instance.setAutoSave(true);//自动保存
            //ConfigurationUtils.dump(instance, System.out);// 设置输出流
        } catch (ConfigurationException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public String readProperty(String key) {
        return instance.getString(key);
    }

    /**
     * 将值对写入到配置文件中
     * @param key
     * @param value
     */
    public void writeProperty(String key, String value) {
        lock.lock();
        instance.setProperty(key, value);
        try {
            instance.save();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static String readJasyptAlgorithm() {
        PropertyHelper propertyHelper = new PropertyHelper(configFilePath);
        return propertyHelper.readProperty("jasypt.Algorithm");
    }

    public static String readJasyptPassword() {
        PropertyHelper propertyHelper = new PropertyHelper(configFilePath);
        return propertyHelper.readProperty("jasypt.password");
    }

    public static String readNclHospitalId() {
        PropertyHelper propertyHelper = new PropertyHelper(configFilePath);
        return propertyHelper.readProperty("ncl.hospital.id");
    }

    public static String readSqlimsFilePath() {
        PropertyHelper propertyHelper = new PropertyHelper(configFilePath);
        return propertyHelper.readProperty("ms.access.sqlims.path");
    }

    //times

    public static String readSyncSqlimsBeginTime() {
        PropertyHelper propertyHelper = new PropertyHelper(timeFilePath);
        return propertyHelper.readProperty("sync.sqlims.begintime");
    }

    public static String readSyncSqlimsEndTime() {
        PropertyHelper propertyHelper = new PropertyHelper(timeFilePath);
        return propertyHelper.readProperty("sync.sqlims.endtime");
    }

}
