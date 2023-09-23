package com.pupilcc.pushbot.controller;

import com.pupilcc.pushbot.entity.DockerWebHookDTO;
import com.pupilcc.pushbot.entity.WorkflowDTO;
import com.pupilcc.pushbot.service.WebhookService;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.servlet.http.HttpServletRequest;

/**
 * WebHook 控制器
 *
 * @author pupilcc
 */
@RestController
@RequestMapping("/webhook")
public class WebhookController {
    private final WebhookService webhookService;

    public WebhookController(WebhookService webhookService) {
        this.webhookService = webhookService;
    }

    /**
     * Telegram Webhook. 接收用户在 Telegram 端发送的消息
     *
     * @param update   消息
     * @param botToken TelegramBotToken
     */
    @PostMapping("/{botToken}")
    public void telegram(@RequestBody Update update, @PathVariable String botToken) {
        webhookService.message(update, botToken);
    }

    /**
     * Docker 自动构建成功消息
     *
     * @param dto       消息
     * @param chatToken 用户Token
     */
    @PostMapping("/docker/{chatToken}")
    public void docker(@RequestBody DockerWebHookDTO dto, @PathVariable String chatToken) {
        webhookService.docker(dto, chatToken);
    }

    /**
     * GitHub Workflow Webhook
     *
     * @param dto       消息
     * @param chatToken 用户Token
     */
    @PostMapping("/workflow/{chatToken}")
    public void workflow(HttpServletRequest request, @RequestBody WorkflowDTO dto, @PathVariable String chatToken) {
        webhookService.workflow(request, dto, chatToken);
    }
}
