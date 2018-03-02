package org.verlet.core.validator.code.sms;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
import org.verlet.core.properties.SecurityProperties;
import org.verlet.core.validator.code.ValidateCode;
import org.verlet.core.validator.code.ValidateCodeGenerator;

/**
 * @author verlet
 * @date 2018/2/27
 */
@Slf4j
@Data
@Component
public class SmsValidateCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ValidateCode generate(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(securityProperties.getValidate().getSmsCode().getVerifySize());
        return new ValidateCode(code,securityProperties.getValidate().getSmsCode().getExpireIn());
    }
}
