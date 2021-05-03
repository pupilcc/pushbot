package com.pupilcc.pushbot.entity;

import com.pengrad.telegrambot.model.request.ParseMode;
import org.springframework.http.codec.multipart.FilePart;

/**
 * 发送图片的参数
 * @author pupilcc
 */
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

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public FilePart  getPhotoFile() {
        return photoFile;
    }

    public void setPhotoFile(FilePart  photoFile) {
        this.photoFile = photoFile;
    }

    public ParseMode getParseMode() {
        return parseMode;
    }

    public void setParseMode(ParseMode parseMode) {
        this.parseMode = parseMode;
    }

    @Override
    public String toString() {
        return "BotPhotoDTO{" +
                "caption='" + caption + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", photoFile=" + photoFile +
                ", parseMode=" + parseMode +
                '}';
    }
}
