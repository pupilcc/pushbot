package com.pupilcc.pushbot.service;

import com.pengrad.telegrambot.model.request.ParseMode;
import com.pupilcc.pushbot.config.BotProperties;
import com.pupilcc.pushbot.entity.SendMessageDTO;
import com.pupilcc.pushbot.entity.DockerWebHookDTO;
import com.pupilcc.pushbot.users.Users;
import com.pupilcc.pushbot.users.UsersRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Webhook 业务处理
 * @author pupilcc
 */
@Service
public class WebhookService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final BotProperties botProperties;
    private final BotUpdateService botUpdateService;
    private final UsersRepository usersRepository;
    private final MessageService messageService;

    public WebhookService(BotProperties botProperties,
                          BotUpdateService botUpdateService,
                          UsersRepository usersRepository,
                          MessageService messageService) {
        this.botProperties = botProperties;
        this.botUpdateService = botUpdateService;
        this.usersRepository = usersRepository;
        this.messageService = messageService;
    }

    /**
     * 接收 Telegram Webhook 消息
     * @param update 消息
     * @param botToken TelegramBotToken
     */
    public void message(Update update, String botToken) {
        // 确保 Webhook 请求来自 Telegram，因此比对 BotToken
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
            SendMessageDTO messageDTO = new SendMessageDTO();
            messageDTO.setText("Docker Hub 自动构建成功" + "\n\n" +
                    dto.getRepository().getRepoName() + " 构建于 " + dto.getPushData().getTag() + "\n\n" +
                    "[查看镜像](" + dto.getRepository().getRepoUrl() + ")");
            messageDTO.setParseMode(ParseMode.Markdown);
            messageService.sendMessage(messageDTO, chatToken);
        } else {
            log.info("用户 Token:{} 不存在", chatToken);
        }
    }
}
