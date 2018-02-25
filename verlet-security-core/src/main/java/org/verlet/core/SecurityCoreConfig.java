package org.verlet.core;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.verlet.core.properties.SecurityProperties;

/**
 * @author verlet
 * @date 2018/2/23
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {
}
