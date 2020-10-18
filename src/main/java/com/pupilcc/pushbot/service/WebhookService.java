package com.pupilcc.pushbot.service;

import com.pupilcc.pushbot.config.BotProperties;
import com.pupilcc.pushbot.entity.DockerWebHookDTO;
import com.pupilcc.pushbot.users.Users;
import com.pupilcc.pushbot.users.UsersRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Webhook 业务处理
 * @author pupilcc
 */
@Service
public class WebhookService {
    private final BotProperties botProperties;
    private final BotUpdateService botUpdateService;
    private final UsersRepository usersRepository;

    public WebhookService(BotProperties botProperties,
                          BotUpdateService botUpdateService,
                          UsersRepository usersRepository) {
        this.botProperties = botProperties;
        this.botUpdateService = botUpdateService;
        this.usersRepository = usersRepository;
    }

    /**
     * 接收 Telegram Webhook 消息
     * @param update 消息
     * @param botToken TelegramBotToken
     */
    public void message(Update update, String botToken) {
        if (botToken.equals(botProperties.getToken())) {
            botUpdateService.process(update);
        }
    }

    /**
     * Docker 自动构建成功消息
     * @param dto 消息
     * @param chatToken 用户Token
     */
    public void webhookDocker(DockerWebHookDTO dto, String chatToken) {
        // 检查 chatToken
        Users users = usersRepository.findByChatToken(chatToken);
        if (ObjectUtils.isNotEmpty(users)) {
            // 推送消息
        }
    }
}
