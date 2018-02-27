package org.verlet.core.properties;

import lombok.Data;

/**
 * 验证参数配置
 *
 * @author verlet
 * @date 2018/2/27
 */
@Data
public class ValidateProperties {
    private ImageCodeProperties imageCode = new ImageCodeProperties();
}
