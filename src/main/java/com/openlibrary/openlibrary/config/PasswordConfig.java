package com.openlibrary.openlibrary.config;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PasswordConfig {

    @Bean
    public PooledPBEStringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        encryptor.setPassword("PleaseEncrypt");
        encryptor.setPoolSize(10);
        return encryptor;
    }
}
