package com.pupilcc.pushbot.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Docker WebHook 消息 repository 对象
 * @author pupilcc
 */
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

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDockerfile() {
        return dockerfile;
    }

    public void setDockerfile(String dockerfile) {
        this.dockerfile = dockerfile;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public Boolean getOfficial() {
        return isOfficial;
    }

    public void setOfficial(Boolean official) {
        isOfficial = official;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }

    public void setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public Boolean getTrusted() {
        return isTrusted;
    }

    public void setTrusted(Boolean trusted) {
        isTrusted = trusted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public String getRepoUrl() {
        return repoUrl;
    }

    public void setRepoUrl(String repoUrl) {
        this.repoUrl = repoUrl;
    }

    public String getStarCount() {
        return starCount;
    }

    public void setStarCount(String starCount) {
        this.starCount = starCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DockerWebHookRepositoryDTO{" +
                "commentCount=" + commentCount +
                ", dateCreated=" + dateCreated +
                ", description='" + description + '\'' +
                ", dockerfile='" + dockerfile + '\'' +
                ", fullDescription='" + fullDescription + '\'' +
                ", isOfficial=" + isOfficial +
                ", isPrivate=" + isPrivate +
                ", isTrusted=" + isTrusted +
                ", name='" + name + '\'' +
                ", namespace='" + namespace + '\'' +
                ", owner='" + owner + '\'' +
                ", repoName='" + repoName + '\'' +
                ", repoUrl='" + repoUrl + '\'' +
                ", starCount='" + starCount + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

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
