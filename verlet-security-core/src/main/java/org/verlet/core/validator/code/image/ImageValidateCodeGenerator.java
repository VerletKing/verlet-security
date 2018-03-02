package org.verlet.core.validator.code.image;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.verlet.core.properties.SecurityProperties;
import org.verlet.core.util.VerifyCodeUtils;
import org.verlet.core.validator.code.ValidateCode;
import org.verlet.core.validator.code.ValidateCodeGenerator;

import java.awt.image.BufferedImage;

/**
 * @author verlet
 * @date 2018/2/27
 */
@Slf4j
@Data
public class ImageValidateCodeGenerator implements ValidateCodeGenerator {

    private SecurityProperties securityProperties;

    @Override
    public ValidateCode generate(ServletWebRequest request) {
        //生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(securityProperties.getValidate().getImageCode().getVerifySize());
        log.info("code={}", verifyCode.toLowerCase());
        //生成图片
        int w = ServletRequestUtils.getIntParameter(request.getRequest(), "width", securityProperties.getValidate().getImageCode().getWidth());
        int h = ServletRequestUtils.getIntParameter(request.getRequest(), "height",securityProperties.getValidate().getImageCode().getHeight());
        BufferedImage image = VerifyCodeUtils.getImage(w, h, verifyCode);
        return new ImageCode(image, verifyCode, securityProperties.getValidate().getImageCode().getExpireIn());

    }
}
