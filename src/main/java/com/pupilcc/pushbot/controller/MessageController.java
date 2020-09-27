package com.pupilcc.pushbot.controller;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import com.pupilcc.pushbot.config.BotProperties;
import com.pupilcc.pushbot.extension.ApiErrorCode;
import com.pupilcc.pushbot.extension.ApiResult;
import com.pupilcc.pushbot.service.BotUpdateService;
import com.pupilcc.pushbot.users.Users;
import com.pupilcc.pushbot.users.UsersRepository;
import com.pupilcc.pushbot.entity.BotMessageDTO;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * 消息控制器
 * @author pupilcc
 */
@RestController
public class MessageController {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private BotProperties botProperties;

    @Autowired
    private BotUpdateService botUpdateService;

    @Autowired
    private TelegramBot telegramBot;

    /**
     * 给 bot 推送消息
     * @param dto 消息体
     * @param chatToken 生成的用户token
     */
    @RequestMapping("/sendMessage/{chatToken}")
    public ApiResult<Object> send(BotMessageDTO dto, @PathVariable String chatToken) {
        // 查找用户
        Users users = usersRepository.findByChatToken(chatToken);
        // 用户不存在
        if (ObjectUtils.isEmpty(users)) {
            return ApiResult.failed(ApiErrorCode.USER_NOT_EXIST);
        }

        // 参数检查
        if (ObjectUtils.isEmpty(dto) || StringUtils.isBlank(dto.getText())) {
            return ApiResult.failed(ApiErrorCode.TEXT_NULL);
        }

        // 给用户发送信息
        SendMessage sendMessage = new SendMessage(users.getChatId(), dto.getText());
        if (ObjectUtils.isNotEmpty(dto.getParseMode())) {
            sendMessage.parseMode(dto.getParseMode());
        }
        SendResponse sendResponse = telegramBot.execute(sendMessage);
        return ApiResult.success(sendResponse.isOk());
    }

    /**
     * Telegram WebHook. 接收用户在 Telegram 端发送的消息
     * @param update 消息
     * @param botToken TelegramBotToken
     */
    @RequestMapping("/webhook/{botToken}")
    public void webhook(@RequestBody Update update, @PathVariable String botToken) {
        if (botToken.equals(botProperties.getToken())) {
            botUpdateService.process(update);
        }
    }
}
