package com.kod95.truckmanagmentsystem.config;

import com.kod95.truckmanagmentsystem.utils.EncryptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class AppConfig {


    @Value("${encode-algorithm}")
    private String algorithm;

    @Value("${encryption.secret-key}")
    private String secretKey;

    @Bean
    public EncryptionUtils encryptionUtils() {
        return new EncryptionUtils(algorithm, secretKey);
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

}
