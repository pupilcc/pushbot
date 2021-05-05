package com.pupilcc.pushbot.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

/**
 * Docker WebHook 消息 push_data 对象
 * @author pupilcc
 */
@Data
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

    /**
     * 兼容下划线字段
     */
    public void setPushed_at(String pushedAt) {
        this.pushedAt = pushedAt;
    }
}
