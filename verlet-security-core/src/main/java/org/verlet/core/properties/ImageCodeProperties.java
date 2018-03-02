package org.verlet.core.properties;

import lombok.Data;

/**
 * @author verlet
 * @date 2018/2/27
 */
@Data
public class ImageCodeProperties extends SmsCodeProperties {
    /**
     * 验证码图片的宽度
     */
    private int width = 100;
    /**
     * 验证码图片的高度
     */
    private int height = 30;

    public ImageCodeProperties() {
        setVerifySize(4);
    }
}
