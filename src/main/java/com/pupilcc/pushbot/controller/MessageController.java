package com.pupilcc.pushbot.controller;

import com.pupilcc.pushbot.entity.SendMessageDTO;
import com.pupilcc.pushbot.entity.TemplateMessageDTO;
import com.pupilcc.pushbot.extension.ApiResult;
import com.pupilcc.pushbot.service.MessageService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @RequestMapping("/sendMessage/{chatToken}")
    public ApiResult<Object> sendMessage(SendMessageDTO dto, @PathVariable String chatToken) {
        return messageService.sendMessage(dto, chatToken);
    }

    /**
     * 给用户发送模板消息
     *
     * @param message       请求参数
     * @param chatToken 用户token
     * @return 响应消息
     */
    @RequestMapping("/sendTemplate/{chatToken}")
    public ApiResult<Object> sendTemplateMessage(TemplateMessageDTO message, @PathVariable String chatToken) {
        return messageService.sendTemplate(message, chatToken);
    }
}