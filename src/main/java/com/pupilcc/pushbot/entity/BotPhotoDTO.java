package com.pupilcc.pushbot.entity;

import com.pengrad.telegrambot.model.request.ParseMode;
import lombok.Data;
import org.springframework.http.codec.multipart.FilePart;

/**
 * 发送图片的参数
 * @author pupilcc
 */
@Data
public class BotPhotoDTO {
    /**
     * 图片标题
     */
    private String caption;

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
}
