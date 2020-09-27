package com.pupilcc.pushbot.entity;

import com.pengrad.telegrambot.model.request.ParseMode;

/**
 * 发送消息的参数
 * @author pupilcc
 */
public class BotMessageDTO {
    /**
     * 文本内容
     */
    private String text;

    /**
     * 格式化选项
     */
    private ParseMode parseMode;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ParseMode getParseMode() {
        return parseMode;
    }

    public void setParseMode(ParseMode parseMode) {
        this.parseMode = parseMode;
    }

    /**
     * 兼容 Telegram API 参数写法
     */
    public void setParse_mode(ParseMode parseMode) {
        this.parseMode = parseMode;
    }

    @Override
    public String toString() {
        return "BotMessageDTO{" +
                "text='" + text + '\'' +
                ", parseMode=" + parseMode +
                '}';
    }
}
