package com.pupilcc.pushbot.entity;

import lombok.Data;

/**
 * Docker WebHook 消息
 * @author pupilcc
 */
@Data
public class DockerWebHookDTO {
    /**
     * 回调地址
     */
    private String callbackUrl;

    /**
     * 推送数据
     */
    private DockerWebHookPushDataDTO pushData;

    /**
     * 仓库数据
     */
    private DockerWebHookRepositoryDTO repository;

    /**
     * 兼容下划线字段
     */
    public void setPush_data(DockerWebHookPushDataDTO pushData) {
        this.pushData = pushData;
    }
}
