package org.verlet.core.social.qq.config;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @author verlet
 * @date 2018/3/5
 */
public class VerletSpringSocialConfigurer extends SpringSocialConfigurer {

    private String fileProcessesUrl;

    public VerletSpringSocialConfigurer(String fileProcessesUrl) {
        this.fileProcessesUrl = fileProcessesUrl;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected <T> T postProcess(T filter) {
        SocialAuthenticationFilter socialAuthenticationFilter = (SocialAuthenticationFilter) super.postProcess(filter);
        socialAuthenticationFilter.setFilterProcessesUrl(fileProcessesUrl);
        return (T) socialAuthenticationFilter;

    }
}
