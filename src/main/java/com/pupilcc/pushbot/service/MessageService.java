package com.pupilcc.pushbot.service;

import cn.hutool.core.util.IdUtil;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import com.pengrad.telegrambot.response.SendResponse;
import com.pupilcc.pushbot.entity.BotMessageDTO;
import com.pupilcc.pushbot.entity.BotPhotoDTO;
import com.pupilcc.pushbot.extension.ApiErrorCode;
import com.pupilcc.pushbot.extension.ApiResult;
import com.pupilcc.pushbot.users.Users;
import com.pupilcc.pushbot.users.UsersRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * 消息的业务处理
 * @author pupilcc
 */
@Service
public class MessageService {
    private final UsersRepository usersRepository;
    private final TelegramBot telegramBot;

    public MessageService(UsersRepository usersRepository, TelegramBot telegramBot) {
        this.usersRepository = usersRepository;
        this.telegramBot = telegramBot;
    }

    /**
     * 发送消息
     * @param dto 消息内容
     * @param chatToken 用户Token
     * @return 响应信息
     */
    public ApiResult<Object> sendMessage(BotMessageDTO dto, String chatToken) {
        // 查找用户
        Users users = usersRepository.findByChatToken(chatToken);
        // 用户不存在
        if (ObjectUtils.isEmpty(users)) {
            return ApiResult.failed(ApiErrorCode.USER_NOT_EXIST);
        }

        // 参数校验
        ApiResult apiResult = checkParameter(dto);
        if (!apiResult.ok()) {
            return apiResult;
        }

        boolean isSend = sendMessage(dto.getText(), dto.getParseMode(), users.getChatId());
        return ApiResult.success(isSend);
    }

    /**
     * 发送消息
     * @param text 消息内容
     * @param parseMode 消息格式
     * @param chatId 用户id
     * @return 响应信息
     */
    private boolean sendMessage(String text, ParseMode parseMode, Long chatId) {
        // 给用户发送信息
        SendMessage sendMessage = new SendMessage(chatId, text);
        if (ObjectUtils.isNotEmpty(parseMode)) {
            sendMessage.parseMode(parseMode);
        }
        SendResponse sendResponse = telegramBot.execute(sendMessage);
        return sendResponse.isOk();
    }

    /**
     * 发送图片
     * @param dto 消息内容
     * @param chatToken 用户Token
     * @return 响应信息
     */
    public ApiResult<Object> sendPhoto(BotPhotoDTO dto, String chatToken) {
        // 查找用户
        Users users = usersRepository.findByChatToken(chatToken);
        // 用户不存在
        if (ObjectUtils.isEmpty(users)) {
            return ApiResult.failed(ApiErrorCode.USER_NOT_EXIST);
        }

        // 参数校验
        ApiResult apiResult = checkParameter(dto);
        if (!apiResult.ok()) {
            return apiResult;
        }

        boolean isSend = false;
        if (StringUtils.isNotBlank(dto.getPhotoUrl())) {
            isSend = sendPhoto(dto.getCaption(), dto.getPhotoUrl(), dto.getParseMode(), users.getChatId());
        } else if (ObjectUtils.isNotEmpty(dto.getPhotoFile())){
            FilePart filePart = dto.getPhotoFile();
            String fileName = filePart.filename();
            String prefix= fileName.substring(fileName.lastIndexOf("."));

            final File file;
            try {
                file = File.createTempFile(IdUtil.simpleUUID(), prefix);
                filePart.transferTo(file);
                isSend = sendPhoto(dto.getCaption(), file, dto.getParseMode(), users.getChatId());
                file.delete();
            } catch (IOException e) {
                e.printStackTrace();
                return ApiResult.failed(ApiErrorCode.FAILED);
            }
        }
        return ApiResult.success(isSend);
    }

    /**
     * 发送图片
     * @param text 消息内容
     * @param parseMode 消息格式
     * @param photoUrl 图片链接
     * @param chatId 用户id
     * @return 响应信息
     */
    public boolean sendPhoto(String text, String photoUrl, ParseMode parseMode, Long chatId) {
        SendPhoto sendPhoto = new SendPhoto(chatId, photoUrl);
        return sendPhoto(sendPhoto, text, parseMode);
    }

    /**
     * 发送图片
     * @param text 消息内容
     * @param parseMode 消息格式
     * @param photoFile 图片文件
     * @param chatId 用户id
     * @return 响应信息
     */
    public boolean sendPhoto(String text, File photoFile, ParseMode parseMode, Long chatId) {
        SendPhoto sendPhoto = new SendPhoto(chatId, photoFile);
        return sendPhoto(sendPhoto, text, parseMode);
    }

    /**
     * 发送图片
     * @param sendPhoto 图片对象
     * @param text 消息内容
     * @param parseMode 消息格式
     * @return 响应信息
     */
    private boolean sendPhoto(SendPhoto sendPhoto, String text, ParseMode parseMode) {
        if (ObjectUtils.isNotEmpty(parseMode)) {
            sendPhoto.parseMode(parseMode);
        }
        if (StringUtils.isNotBlank(text)) {
            sendPhoto.caption(text);
        }
        SendResponse sendResponse = telegramBot.execute(sendPhoto);
        return sendResponse.isOk();
    }

    /**
     * 参数校验
     * @param dto 消息内容
     * @return 业务码
     */
    private ApiResult checkParameter(BotMessageDTO dto) {
        if (ObjectUtils.isEmpty(dto)) {
            return ApiResult.failed(ApiErrorCode.PARAMETER_NULL);
        }
        if (StringUtils.isBlank(dto.getText())) {
            return ApiResult.failed(ApiErrorCode.TEXT_NULL);
        }

        return ApiResult.success();
    }

    /**
     * 参数校验
     * @param dto 消息内容
     * @return 业务码
     */
    private ApiResult checkParameter(BotPhotoDTO dto) {
        if (ObjectUtils.isEmpty(dto)) {
            return ApiResult.failed(ApiErrorCode.PARAMETER_NULL);
        }

        boolean isExistPhoto = ObjectUtils.isNotEmpty(dto.getPhotoFile()) || StringUtils.isNotBlank(dto.getPhotoUrl());
        if (!isExistPhoto) {
            return ApiResult.failed(ApiErrorCode.IMG_NULL);
        }

        return ApiResult.success();
    }
}
