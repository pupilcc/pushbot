package com.pupilcc.pushbot.service;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * 消息的业务处理
 *
 * @author pupilcc
 */
@Component
public class BotUpdateService {
    /**
     * 消息处理
     *
     * @param update 用户给 bot 发送的消息
     */
    public void process(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            // 判断消息是命令还是对话
            char ch = update.getMessage().getText().charAt(0);
            boolean isCommand = ch == '/';
            if (isCommand) {
                String text = update.getMessage().getText().substring(1);
                Long chatId = update.getMessage().getChatId();
                if (BotMessageServiceEnum.isExist(text)) {
                    BotMessageServiceEnum.valueOf(text).operation(chatId);
                }
            }
        }
    }
}
