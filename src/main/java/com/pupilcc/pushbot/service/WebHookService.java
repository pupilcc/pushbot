package com.pupilcc.pushbot.service;

import com.pupilcc.pushbot.config.BotProperties;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * WebHook 业务处理
 * @author pupilcc
 */
@Service
public class WebHookService {
    private final BotProperties botProperties;

    private final BotUpdateService botUpdateService;

    public WebHookService(BotProperties botProperties, BotUpdateService botUpdateService) {
        this.botProperties = botProperties;
        this.botUpdateService = botUpdateService;
    }

    /**
     * 接收 Telegram WebHook 消息
     * @param update 消息
     * @param botToken TelegramBotToken
     */
    public void message(Update update, String botToken) {
        if (botToken.equals(botProperties.getToken())) {
            botUpdateService.process(update);
        }
    }
}
