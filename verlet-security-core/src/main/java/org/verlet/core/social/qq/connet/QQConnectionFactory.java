package org.verlet.core.social.qq.connet;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.verlet.core.social.qq.api.QQ;

/**
 * @author verlet
 * @date 2018/3/4
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

    public QQConnectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
    }
}
