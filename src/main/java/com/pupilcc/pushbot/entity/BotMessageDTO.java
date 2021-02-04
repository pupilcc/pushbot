package com.pupilcc.pushbot.entity;

import com.pengrad.telegrambot.model.request.ParseMode;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

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
     * 图片链接
     */
    private String photoUrl;

    /**
     * 图片文件
     */
    private MultipartFile photoFile;

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

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public MultipartFile getPhotoFile() {
        return photoFile;
    }

    public void setPhotoFile(MultipartFile photoFile) {
        this.photoFile = photoFile;
    }

    @Override
    public String toString() {
        return "BotMessageDTO{" +
                "text='" + text + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", photoFile=" + photoFile +
                ", parseMode=" + parseMode +
                '}';
    }
}
