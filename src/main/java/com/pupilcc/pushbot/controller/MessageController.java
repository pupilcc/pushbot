package com.pupilcc.pushbot.controller;

import com.pupilcc.common.rest.ApiResult;
import com.pupilcc.pushbot.dto.MessageDTO;
import com.pupilcc.pushbot.service.MessageService;
import org.springframework.web.bind.annotation.*;

/**
 * 消息控制器
 *
 * @author pupilcc
 */
@RestController
@RequestMapping("/message")
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
}