package com.pupilcc.pushbot.controller;

import com.pupilcc.common.rest.ApiResult;
import com.pupilcc.pushbot.dto.MessageDTO;
import com.pupilcc.pushbot.dto.TemplateMessageDTO;
import com.pupilcc.pushbot.service.MessageService;
import org.springframework.web.bind.annotation.*;

/**
 * 消息控制器
 *
 * @author pupilcc
 */
@RestController
@Deprecated
public class OldMessageController {
    private final MessageService messageService;

    public OldMessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * 给用户发送消息
     *
     * @param dto       消息体
     * @param chatToken 用户token
     * @return 响应消息
     */
    @GetMapping("/sendMessage/{chatToken}")
    public ApiResult<Object> sendMessage(MessageDTO dto, @PathVariable String chatToken) {
        return messageService.sendMessage(dto, chatToken);
    }

    /**
     * 给用户发送消息
     *
     * @param dto       消息体
     * @param chatToken 用户token
     * @return 响应消息
     */
    @PostMapping("/sendMessage/{chatToken}")
    public ApiResult<Object> sendMessageByJson(@RequestBody MessageDTO dto, @PathVariable String chatToken) {
        return messageService.sendMessage(dto, chatToken);
    }

    /**
     * 给用户发送模板消息
     *
     * @param message       请求参数
     * @param chatToken 用户token
     * @return 响应消息
     */
    @GetMapping("/sendTemplate/{chatToken}")
    public ApiResult<Object> sendTemplateMessage(TemplateMessageDTO message, @PathVariable String chatToken) {
        return messageService.sendTemplate(message, chatToken);
    }

    /**
     * 给用户发送模板消息
     *
     * @param message       请求参数
     * @param chatToken 用户token
     * @return 响应消息
     */
    @PostMapping("/sendTemplate/{chatToken}")
    public ApiResult<Object> sendTemplateMessageByJson(@RequestBody TemplateMessageDTO message, @PathVariable String chatToken) {
        return messageService.sendTemplate(message, chatToken);
    }
}