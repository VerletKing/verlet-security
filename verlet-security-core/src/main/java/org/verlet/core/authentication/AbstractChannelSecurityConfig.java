package org.verlet.core.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.verlet.core.properties.SecurityConstants;

/**
 * 抽象SecurityConfig  表单配置
 *
 * @author verlet
 * @date 2018/3/1
 */
public abstract class AbstractChannelSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler verletAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler verletAuthenticationFailureHandler;

    protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .loginPage(SecurityConstants.DEFAULT_LOGIN_PAGE_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .successHandler(verletAuthenticationSuccessHandler)
                .failureHandler(verletAuthenticationFailureHandler);
    }


}
