package org.verlet.core.properties;

import lombok.Data;

/**
 * @author verlet
 * @date 2018/2/27
 */
@Data
public class ImageCodeProperties {
    /**
     * 验证码图片的宽度
     */
    private int width = 100;
    /**
     * 验证码图片的高度
     */
    private int height = 30;
    /**
     * 验证码的长度
     */
    private int verifySize = 5;
    /**
     * 验证码的有效时间单位秒
     */
    private int expireIn = 60 * 3;
    /**
     * 需要拦截的URL
     */
    private String url;
}
