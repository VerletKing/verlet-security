package org.verlet.core.validator.code.image;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
import org.verlet.core.validator.code.impl.AbstractValidateCodeProcessor;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * 图片验证码处理器
 *
 * @author verlet
 * @date 2018/2/28
 */
@Component
public class ImageValidateCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {

    @Override
    protected void send(ServletWebRequest request, ImageCode validateCode) throws IOException {
        request.getResponse().setHeader("pragma", "no-cache");
        request.getResponse().setHeader("cache-control", "no-cache");
        request.getResponse().setHeader("expires", "0");
        ImageIO.write(validateCode.getImage(), "JPEG", request.getResponse().getOutputStream());
    }
}
