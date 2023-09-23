package com.pupilcc.pushbot.controller;

import com.pupilcc.pushbot.entity.DockerWebHookDTO;
import com.pupilcc.pushbot.service.WebhookService;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * WebHook 控制器
 *
 * @author pupilcc
 */
@RestController
@RequestMapping("/webhook")
public class WebhookController {
    private final WebhookService webHookService;

    public WebhookController(WebhookService webHookService) {
        this.webHookService = webHookService;
    }

    /**
     * Telegram Webhook. 接收用户在 Telegram 端发送的消息
     *
     * @param update   消息
     * @param botToken TelegramBotToken
     */
    @PostMapping("/{botToken}")
    public void webhookTelegram(@RequestBody Update update, @PathVariable String botToken) {
        webHookService.message(update, botToken);
    }

    /**
     * Docker 自动构建成功消息
     *
     * @param dto       消息
     * @param chatToken 用户Token
     */
    @PostMapping("/docker/{chatToken}")
    public void webhookDocker(@RequestBody DockerWebHookDTO dto, @PathVariable String chatToken) {
        webHookService.webhookDocker(dto, chatToken);
    }
}
