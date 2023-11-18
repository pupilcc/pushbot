package com.pupilcc.pushbot.dto;

import lombok.Data;

/**
 * 模板消息 DTO
 *
 * @author pupilcc
 * @since 2022-06-23
 */
@Data
public class TemplateMessageDTO {
    /**
     * 消息模板id
     */
    private Integer templateId;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;
}
