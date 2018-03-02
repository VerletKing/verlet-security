package org.verlet.core.properties;

import lombok.Data;

/**
 * @author verlet
 * @date 2018/2/28
 */
@Data
public class SmsCodeProperties {
    /**
     * 验证码的长度
     */
    private int verifySize = 6;
    /**
     * 验证码的有效时间单位秒
     */
    private int expireIn = 60 * 3;
    /**
     * 需要拦截的URL
     */
    private String url;

}
