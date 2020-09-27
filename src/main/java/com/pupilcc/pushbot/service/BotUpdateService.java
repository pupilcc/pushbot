package com.pupilcc.pushbot.service;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * 消息的业务处理
 * @author pupilcc
 */
@Component
public class BotUpdateService {
    /**
     * 消息处理
     * @param update 用户给 bot 发送的消息
     */
    public void process(Update update) {
        String text = update.getMessage().getText().substring(1);
        Long chatId = update.getMessage().getChatId();
        BotMessageServiceEnum.valueOf(text).operation(chatId);
    }
}
