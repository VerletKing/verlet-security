package org.verlet.core.validator.code.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;
import org.verlet.core.properties.SecurityConstants;
import org.verlet.core.properties.SecurityProperties;
import org.verlet.core.validator.code.ValidateCodeProcessorHolder;
import org.verlet.core.validator.code.exception.ValidateCodeException;
import org.verlet.core.validator.code.properties.ValidateCodeType;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

import static org.verlet.core.validator.code.ValidateCodeProcessor.SESSION_KEY_PREFIX;

/**
 * @author verlet
 * @date 2018/2/26
 */
@Slf4j
@Component
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    /**
     * 验证码校验失败处理器
     */
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    /**
     * 系统配置信息
     */
    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 系统中的校验码处理器
     */
    @Autowired
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    /**
     * 存放所有需要校验验证码的URL
     */
    private Map<String, ValidateCodeType> urlMap = new HashMap<>();

    /**
     * 验证请求URL与配置的URL是否匹配的工具类
     */
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    public static final String SESSION_KEY = SESSION_KEY_PREFIX + "IMAGE";

    /**
     * 这个方法将在所有的属性被初始化后调用。
     * 但是会在init前调用。
     * 初始化要拦截的URL配置信息
     *
     * @throws ServletException
     */
    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();

        urlMap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM, ValidateCodeType.IMAGE);
        addUrlToMap(securityProperties.getValidate().getImageCode().getUrl(), ValidateCodeType.IMAGE);

        urlMap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE, ValidateCodeType.SMS);
        addUrlToMap(securityProperties.getValidate().getSmsCode().getUrl(), ValidateCodeType.IMAGE);
    }

    /**
     * 将系统中配置的需要校验验证码的URL根据校验的类型放入map
     *
     * @param urlString
     * @param type
     */
    protected void addUrlToMap(String urlString, ValidateCodeType type) {
        if (StringUtils.isNotBlank(urlString)) {
            Arrays.asList(StringUtils.splitByWholeSeparatorPreserveAllTokens(urlString, ","))
                    .forEach(url -> urlMap.put(url, type));
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ValidateCodeType type = getValidateCodeType(request);
        if (type != null) {
            log.info("校验请求（{}）中的验证码，验证码类型{}", request.getRequestURI(), type);
            try {
                validateCodeProcessorHolder.findValidateCodeProcessor(type).validate(new ServletWebRequest(request, response));
                log.info("验证码校验通过");
            } catch (ValidateCodeException exception) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, exception);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private ValidateCodeType getValidateCodeType(HttpServletRequest request) {
        AtomicReference<ValidateCodeType> result = new AtomicReference<>();
        if (StringUtils.equalsIgnoreCase(request.getMethod(), "post")) {
            Set<String> urls = urlMap.keySet();
            urls.forEach(url -> {
                if (pathMatcher.match(url, request.getRequestURI())) {
                    result.set(urlMap.get(url));
                }
            });
        }
        return result.get();
    }

}
