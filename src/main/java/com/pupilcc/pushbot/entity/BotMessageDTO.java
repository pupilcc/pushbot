package com.pupilcc.pushbot.entity;

import com.pengrad.telegrambot.model.request.ParseMode;
import lombok.Data;
import org.springframework.http.codec.multipart.FilePart;

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
     * 图片链接
     */
    private String photoUrl;

    /**
     * 图片文件
     */
    private FilePart photoFile;

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
