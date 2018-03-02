package org.verlet.core.validator.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.verlet.core.properties.SecurityProperties;
import org.verlet.core.validator.code.ValidateCodeGenerator;
import org.verlet.core.validator.code.image.ImageValidateCodeGenerator;
import org.verlet.core.validator.code.sms.DefaultSmsCodeSender;
import org.verlet.core.validator.code.sms.SmsCodeSender;

/**
 * @author verlet
 * @date 2018/2/27
 */
@Configuration
public class ValidateCodeBeanConfig {
    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    @ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
    public ValidateCodeGenerator imageValidateCodeGenerator() {
        ImageValidateCodeGenerator imageValidateCodeGenerator = new ImageValidateCodeGenerator();
        imageValidateCodeGenerator.setSecurityProperties(securityProperties);
        return imageValidateCodeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender() {
        return new DefaultSmsCodeSender();
    }
}
