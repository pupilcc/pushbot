package com.pupilcc.pushbot.entity;

/**
 * Docker WebHook 消息
 * @author pupilcc
 */
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

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public DockerWebHookPushDataDTO getPushData() {
        return pushData;
    }

    public void setPushData(DockerWebHookPushDataDTO pushData) {
        this.pushData = pushData;
    }

    public DockerWebHookRepositoryDTO getRepository() {
        return repository;
    }

    public void setRepository(DockerWebHookRepositoryDTO repository) {
        this.repository = repository;
    }

    @Override
    public String toString() {
        return "DockerWebHookDTO{" +
                "callbackUrl='" + callbackUrl + '\'' +
                ", pushData=" + pushData +
                ", repository=" + repository +
                '}';
    }

    /**
     * 兼容下划线字段
     */
    public void setPush_data(DockerWebHookPushDataDTO pushData) {
        this.pushData = pushData;
    }
}
