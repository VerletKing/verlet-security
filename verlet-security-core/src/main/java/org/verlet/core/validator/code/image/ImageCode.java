package org.verlet.core.validator.code.image;

import lombok.Data;
import org.verlet.core.validator.code.ValidateCode;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * 图片验证码
 *
 * @author verlet
 * @date 2018/2/26
 */
@Data
public class ImageCode extends ValidateCode {

    private BufferedImage image;

    public ImageCode(String code, LocalDateTime expireTime, BufferedImage image) {
        super(code, expireTime);
        this.image = image;
    }

    public ImageCode(BufferedImage image, String code, int expireIn ) {
       super(code,expireIn);
        this.image = image;
    }
}
