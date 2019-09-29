package com.lm.ticker.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author LM
 * @version v1.0
 * @description:
 * @date 2019/9/29 9:33
 */
@ConfigurationProperties(prefix = "config-time")
@Component
@Data
public class LoopTimeConfig {

    private Integer randomTime = 20 * 1000;

}
