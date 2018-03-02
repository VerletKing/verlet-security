package org.verlet.browser.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.verlet.core.common.ServerResponse;
import org.verlet.core.properties.SecurityConstants;
import org.verlet.core.properties.SecurityProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author verlet
 * @date 2018/2/23
 */
@RestController
@Slf4j
public class BrowserSecurityController {

    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 当需要身份认证时，就跳转到这里
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public ServerResponse<String> requestAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request,response);
        if(savedRequest!=null){
            String redirectUrl = savedRequest.getRedirectUrl();
            log.info("引发跳转的请求是：{}",redirectUrl);
            if(StringUtils.endsWithIgnoreCase(redirectUrl,".html")){
                redirectStrategy.sendRedirect(request,response,securityProperties.getBrowser().getLoginPage());
            }
        }
        return ServerResponse.createByErrorCodeMessage(
                HttpStatus.UNAUTHORIZED.value(),
                "访问的服务需要身份认证，请引导用户到登录页");
    }
}
