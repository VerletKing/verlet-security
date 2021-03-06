package org.verlet.core.validator.code.properties;

import org.verlet.core.properties.SecurityConstants;

/**
 * @author verlet
 * @date 2018/3/1
 */
public enum ValidateCodeType {


    /**
     * 短信验证码
     */
    SMS {
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_SMS;
        }
    },

    /**
     * 图片验证码
     */
    IMAGE {
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_IMAGE;
        }
    };

    /**
     * 校验时请求中获取参数的名字
     *
     * @return
     */
    public abstract String getParamNameOnValidate();
}
