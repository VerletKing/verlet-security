package org.verlet.core.validator.code;

import javax.servlet.http.HttpServletRequest;

/**
 * @author verlet
 * @date 2018/2/27
 */
public interface ValidateCodeGenerator {

    ImageCode generate(HttpServletRequest request);
}
