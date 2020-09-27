package com.pupilcc.pushbot.service;

/**
 * Bot 消息的业务处理接口
 * @author pupilcc
 */
public interface IBotMessageService {
    /**
     * 消息的业务处理
     * @param chatId 用户id
     */
    void operation(Long chatId);
}
