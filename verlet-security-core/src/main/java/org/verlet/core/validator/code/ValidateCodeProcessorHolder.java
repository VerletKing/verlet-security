package org.verlet.core.validator.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.verlet.core.validator.code.exception.ValidateCodeException;
import org.verlet.core.validator.code.properties.ValidateCodeType;

import java.util.Map;

/**
 * 根据type
 *
 * @author verlet
 * @date 2018/3/1
 */
@Component
public class ValidateCodeProcessorHolder {

    @Autowired
    private Map<String,ValidateCodeProcessor> validateCodeProcessorMap;

    public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType type){
        return findValidateCodeProcessor(type.toString().toLowerCase());
    }

    public ValidateCodeProcessor findValidateCodeProcessor(String type){
        String name = type.toLowerCase() + ValidateCodeProcessor.class.getSimpleName();
        ValidateCodeProcessor processor = validateCodeProcessorMap.get(name);
        if (processor==null){
            throw new ValidateCodeException("验证码处理器" + name + "不存在");
        }
        return processor;
    }
}
