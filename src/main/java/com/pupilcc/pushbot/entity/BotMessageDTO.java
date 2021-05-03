package com.pupilcc.pushbot.entity;

import com.pengrad.telegrambot.model.request.ParseMode;
import lombok.Data;

/**
 * 发送消息的参数
 * @author pupilcc
 */
@Data
public class BotMessageDTO {
    /**
     * 文本内容
     */
    private String text;

    /**
     * 格式化选项
     */
    private ParseMode parseMode;

    /**
     * 兼容 Telegram API 参数写法
     */
    public void setParse_mode(ParseMode parseMode) {
        this.parseMode = parseMode;
    }
}
