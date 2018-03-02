package org.verlet.core.validator.code.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 验证码异常类
 *
 * @author verlet
 * @date 2018/2/26
 */
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
