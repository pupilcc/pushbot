package com.pupilcc.pushbot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import com.pupilcc.pushbot.config.BotProperties;
import com.pupilcc.pushbot.config.SpringContextHolder;
import com.pupilcc.pushbot.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理消息
 * @author pupilcc
 */
public enum BotMessageServiceEnum implements IBotMessageService {
    /**
     * 生成/显示推送链接
     */
    start {
        private final BotProperties botProperties = SpringContextHolder.getBean(BotProperties.class);
        private final LinkService linkService = SpringContextHolder.getBean(LinkService.class);
        private final TelegramBot telegramBot = SpringContextHolder.getBean(TelegramBot.class);

        @Override
        public void operation(Long chatId) {
            String url = linkService.create(chatId);
            String startHint = String.format(botProperties.getStartHint(), url);
            StringBuilder text = StringUtils.replaceLine(startHint);
            SendMessage sendMessage = new SendMessage(chatId, text.toString());
            sendMessage.parseMode(ParseMode.Markdown);
            telegramBot.execute(sendMessage);
        }
    },

    /**
     * 删除推送链接
     */
    end {
        private final BotProperties botProperties = SpringContextHolder.getBean(BotProperties.class);
        private final LinkService linkService = SpringContextHolder.getBean(LinkService.class);
        private final TelegramBot telegramBot = SpringContextHolder.getBean(TelegramBot.class);

        @Override
        public void operation(Long chatId) {
            linkService.delete(chatId);
            String endHint = botProperties.getEndHint();
            StringBuilder text = StringUtils.replaceLine(endHint);
            SendMessage sendMessage = new SendMessage(chatId, text.toString());
            sendMessage.parseMode(ParseMode.Markdown);
            telegramBot.execute(sendMessage);
        }
    };

    /**
     * 是否存在该命令
     * @param command 命令
     * @return true 存在; false 不存在;
     */
    public static boolean isExist(String command) {
        for (BotMessageServiceEnum serviceEnum : BotMessageServiceEnum.values()) {
            if (command.equals(serviceEnum.name())) {
                return true;
            }
        }
        return false;
    }
}
