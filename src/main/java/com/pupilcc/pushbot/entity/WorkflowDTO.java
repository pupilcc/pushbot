package com.pupilcc.pushbot.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

/**
 * GitHub Workflow DTO
 *
 * @author pupilcc
 * @since 2023-09-23
 */
@Data
public class WorkflowDTO {
    private String event;

    private String repository;

    private String commit;

    private String ref;

    private String head;

    private String workflow;

    @JsonProperty("requestID")
    private String requestId;

    private Map<String, String> data;
}
