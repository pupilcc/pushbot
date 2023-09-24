package com.pupilcc.pushbot.controller;

import com.pupilcc.common.rest.ApiResult;
import com.pupilcc.pushbot.entity.MessageDTO;
import com.pupilcc.pushbot.entity.SendMessageDTO;
import com.pupilcc.pushbot.entity.TemplateMessageDTO;
import com.pupilcc.pushbot.service.MessageService;
import org.springframework.web.bind.annotation.*;

/**
 * 消息控制器
 *
 * @author pupilcc
 */
@RestController
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
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
    public ApiResult<Object> message(MessageDTO dto, @PathVariable String chatToken) {
        return messageService.sendMessage(dto, chatToken);
    }

    /**
     * 给用户发送消息
     *
     * @param dto       消息体
     * @param chatToken 用户token
     * @return 响应消息
     */
    @PostMapping("/{chatToken}")
    public ApiResult<Object> sendMessageByJson(@RequestBody MessageDTO dto, @PathVariable String chatToken) {
        return messageService.sendMessage(dto, chatToken);
    }

    /**
     * 给用户发送消息
     *
     * @param dto       消息体
     * @param chatToken 用户token
     * @return 响应消息
     */
    @Deprecated
    @GetMapping("/sendMessage/{chatToken}")
    public ApiResult<Object> sendMessage(SendMessageDTO dto, @PathVariable String chatToken) {
        return messageService.sendMessage(dto, chatToken);
    }

    /**
     * 给用户发送消息
     *
     * @param dto       消息体
     * @param chatToken 用户token
     * @return 响应消息
     */
    @Deprecated
    @PostMapping("/sendMessage/{chatToken}")
    public ApiResult<Object> sendMessageByJson(@RequestBody SendMessageDTO dto, @PathVariable String chatToken) {
        return messageService.sendMessage(dto, chatToken);
    }

    /**
     * 给用户发送模板消息
     *
     * @param message       请求参数
     * @param chatToken 用户token
     * @return 响应消息
     */
    @Deprecated
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
    @Deprecated
    @PostMapping("/sendTemplate/{chatToken}")
    public ApiResult<Object> sendTemplateMessageByJson(@RequestBody TemplateMessageDTO message, @PathVariable String chatToken) {
        return messageService.sendTemplate(message, chatToken);
    }
}