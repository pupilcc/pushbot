package com.pupilcc.pushbot.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Docker WebHook 消息 repository 对象
 *
 * @author pupilcc
 */
@Data
public class DockerWebHookRepositoryDTO {
    private Integer commentCount;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateCreated;

    /**
     * 描述
     */
    private String description;

    /**
     * Dockerfile 文件内容
     */
    private String dockerfile;

    /**
     * 完整描述
     */
    private String fullDescription;

    private Boolean isOfficial;

    /**
     * 是否是私有仓库
     */
    private Boolean isPrivate;

    private Boolean isTrusted;

    /**
     * 仓库名
     */
    private String name;

    /**
     * 命名空间
     */
    private String namespace;

    /**
     * 所有者
     */
    private String owner;

    /**
     * 完整仓库名称（所有者/仓库名）
     */
    private String repoName;

    /**
     * 仓库地址
     */
    private String repoUrl;

    private String starCount;

    /**
     * 状态
     */
    private String status;

    /* 兼容下划线字段 */

    public void setComment_count(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public void setFull_description(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public void setIs_official(Boolean official) {
        isOfficial = official;
    }

    public void setIs_private(Boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public void setIs_trusted(Boolean trusted) {
        isTrusted = trusted;
    }

    public void setRepo_name(String repoName) {
        this.repoName = repoName;
    }

    public void setRepo_url(String repoUrl) {
        this.repoUrl = repoUrl;
    }

    public void setStar_count(String starCount) {
        this.starCount = starCount;
    }
}
