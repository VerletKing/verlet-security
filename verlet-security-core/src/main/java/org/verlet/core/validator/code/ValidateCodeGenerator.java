package org.verlet.core.validator.code;

import org.springframework.web.context.request.ServletWebRequest;
import org.verlet.core.validator.code.ValidateCode;

import javax.servlet.http.HttpServletRequest;

/**
 * @author verlet
 * @date 2018/2/27
 */
public interface ValidateCodeGenerator {

    ValidateCode generate(ServletWebRequest request);
}
