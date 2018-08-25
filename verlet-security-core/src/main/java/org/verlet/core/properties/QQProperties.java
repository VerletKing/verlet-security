package org.verlet.core.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @author verlet
 * @date 2018/3/4
 */
@Data
public class QQProperties extends SocialProperties {
    private String providerId = "qq";

}
