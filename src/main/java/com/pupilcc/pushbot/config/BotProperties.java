package com.pupilcc.pushbot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Bot 配置项
 * @author pupilcc
 */
@Configuration
@ConfigurationProperties(prefix = "bot")
public class BotProperties {
    private String token;
    private String domain;
    private String startHint;
    private String endHint;

    public BotProperties() {}

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getStartHint() {
        return startHint;
    }

    public void setStartHint(String startHint) {
        this.startHint = startHint;
    }

    public String getEndHint() {
        return endHint;
    }

    public void setEndHint(String endHint) {
        this.endHint = endHint;
    }
}
