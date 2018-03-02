package org.verlet.core.validator.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author verlet
 * @date 2018/2/28
 */
public interface ValidateCodeProcessor {

    /**
     * 验证码放入session时的前缀
     */
    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    /**
     * 创建校验码
     *
     * @param request
     */
    void create(ServletWebRequest request) throws Exception;

    /**
     * 校验验证码
     *
     * @param servletWebRequest
     */
    void validate(ServletWebRequest servletWebRequest);


}
