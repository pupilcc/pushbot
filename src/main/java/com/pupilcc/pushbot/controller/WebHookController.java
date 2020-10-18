package com.pupilcc.pushbot.controller;

import com.pupilcc.pushbot.entity.DockerWebHookDTO;
import com.pupilcc.pushbot.service.WebHookService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * WebHook 控制器
 * @author pupilcc
 */
@RestController
@RequestMapping("/webhook")
public class WebHookController {
    private final WebHookService webHookService;

    public WebHookController(WebHookService webHookService) {
        this.webHookService = webHookService;
    }

    /**
     * Telegram WebHook. 接收用户在 Telegram 端发送的消息
     * @param update 消息
     * @param botToken TelegramBotToken
     */
    @RequestMapping("/{botToken}")
    public void webhook(@RequestBody Update update, @PathVariable String botToken) {
        webHookService.message(update, botToken);
    }

    /**
     * Docker WebHook
     * @param dto 消息
     * @param chatToken 用户Token
     */
    @RequestMapping("/docker/notify/{chatToken}")
    public void webhookDocker(@RequestBody DockerWebHookDTO dto, @PathVariable String chatToken) {
        webHookService.webhookDocker(dto, chatToken);
    }
}
