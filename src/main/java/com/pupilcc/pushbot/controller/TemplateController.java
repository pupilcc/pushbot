package com.pupilcc.pushbot.controller;

import com.pupilcc.common.rest.ApiResult;
import com.pupilcc.pushbot.dto.TemplateMessageDTO;
import com.pupilcc.pushbot.service.MessageService;
import org.springframework.web.bind.annotation.*;

/**
 * 模板消息控制器
 *
 * @author pupilcc
 */
@RestController
@RequestMapping("/template")
public class TemplateController {
    private final MessageService messageService;

    public TemplateController(MessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * 给用户发送消息
     *
     * @param dto       消息体
     * @param chatToken 用户token
     * @return 响应消息
     */
    @GetMapping("/{chatToken}")
    public ApiResult<Object> message(TemplateMessageDTO dto, @PathVariable String chatToken) {
        return messageService.sendTemplate(dto, chatToken);
    }

    /**
     * 给用户发送消息
     *
     * @param dto       消息体
     * @param chatToken 用户token
     * @return 响应消息
     */
    @PostMapping("/{chatToken}")
    public ApiResult<Object> sendMessageByJson(@RequestBody TemplateMessageDTO dto, @PathVariable String chatToken) {
        return messageService.sendTemplate(dto, chatToken);
    }
}