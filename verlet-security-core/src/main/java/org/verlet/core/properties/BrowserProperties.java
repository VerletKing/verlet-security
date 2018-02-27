package org.verlet.core.properties;

import lombok.Data;

/**
 * @author verlet
 * @date 2018/2/23
 */
@Data
public class BrowserProperties {
    private String loginPage = "/signIn.html";

    private LoginResponseType loginType = LoginResponseType.JSON;

    private int rememberMeSeconds = 3600;
}
