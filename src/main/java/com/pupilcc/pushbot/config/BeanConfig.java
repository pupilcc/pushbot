package com.pupilcc.pushbot.config;

import com.pengrad.telegrambot.TelegramBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Autowired
    private BotProperties botProperties;

    @Bean
    public TelegramBot telegramBot() {
        return new TelegramBot(botProperties.getToken());
    }
}
