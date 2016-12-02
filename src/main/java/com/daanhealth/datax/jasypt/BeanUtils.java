package com.daanhealth.datax.jasypt;

import com.daanhealth.datax.jasypt.StandardPBEStringEncryptor;

public class BeanUtils {
    public static final String decrypt(final String decryptedMessage){
        StandardPBEStringEncryptor stand = new StandardPBEStringEncryptor();
        return stand.decryptToDto(decryptedMessage);
    }

    public static final String decryptToDB(final String decryptedMessage){
        StandardPBEStringEncryptor stand = new StandardPBEStringEncryptor();
        return stand.decryptToDB(decryptedMessage);
    }

    public static final String encrypt(final String encryptedMessage){
        StandardPBEStringEncryptor stand = new StandardPBEStringEncryptor();
        return stand.encryptToDto(encryptedMessage);
    }
}
