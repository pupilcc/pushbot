package com.pupilcc.pushbot.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

/**
 * Docker WebHook 消息 push_data 对象
 * @author pupilcc
 */
public class DockerWebHookPushDataDTO {
    private List<String> images;

    /**
     * 提交时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String pushedAt;

    /**
     * 作者
     */
    private String pusher;

    /**
     * 镜像标签
     */
    private String tag;

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getPushedAt() {
        return pushedAt;
    }

    public void setPushedAt(String pushedAt) {
        this.pushedAt = pushedAt;
    }

    public String getPusher() {
        return pusher;
    }

    public void setPusher(String pusher) {
        this.pusher = pusher;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "DockerWebHookPushDataDTO{" +
                "images=" + images +
                ", pushedAt='" + pushedAt + '\'' +
                ", pusher='" + pusher + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }

    /**
     * 兼容下划线字段
     */
    public void setPushed_at(String pushedAt) {
        this.pushedAt = pushedAt;
    }
}
