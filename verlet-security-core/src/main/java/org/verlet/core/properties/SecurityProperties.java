package org.verlet.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author verlet
 * @date 2018/2/23
 */
@ConfigurationProperties(prefix = "verlet.security")
@Data
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();

    private ValidateProperties validate = new ValidateProperties();

}
