package com.pupilcc.pushbot.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Bot 配置项
 *
 * @author pupilcc
 */
@Configuration
@ConfigurationProperties(prefix = "bot")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BotProperties {
    private String token;
    private String domain;
    private String startHint;
    private String endHint;
}
