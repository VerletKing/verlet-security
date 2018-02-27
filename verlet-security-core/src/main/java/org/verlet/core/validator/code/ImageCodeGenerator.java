package org.verlet.core.validator.code;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.verlet.core.properties.SecurityProperties;
import org.verlet.core.util.VerifyCodeUtils;

import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;

/**
 * @author verlet
 * @date 2018/2/27
 */
@Slf4j
@Data
public class ImageCodeGenerator implements ValidateCodeGenerator {

    private SecurityProperties securityProperties;

    @Override
    public ImageCode generate(HttpServletRequest request) {
        //生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(securityProperties.getValidate().getImageCode().getVerifySize());
        log.info("code={}", verifyCode.toLowerCase());
        //生成图片
        int w = ServletRequestUtils.getIntParameter(request, "width", securityProperties.getValidate().getImageCode().getWidth());
        int h = ServletRequestUtils.getIntParameter(request, "height",securityProperties.getValidate().getImageCode().getHeight());
        BufferedImage image = VerifyCodeUtils.getImage(w, h, verifyCode);
        return new ImageCode(image, verifyCode, securityProperties.getValidate().getImageCode().getExpireIn());

    }
}
