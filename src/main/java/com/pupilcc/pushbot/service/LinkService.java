package com.pupilcc.pushbot.service;

import com.pupilcc.pushbot.config.BotProperties;
import com.pupilcc.pushbot.users.Users;
import com.pupilcc.pushbot.users.UsersRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 链接的业务处理
 *
 * @author pupilcc
 */
@Service
public class LinkService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private BotProperties botProperties;

    /**
     * 生成/显示链接
     *
     * @param chatId 用户id
     * @return 生成的链接
     */
    public String create(Long chatId) {
        // 显示链接
        Optional<Users> optionalUsers = usersRepository.findById(chatId);
        if (optionalUsers.isPresent()) {
            Users users = optionalUsers.get();
            if (ObjectUtils.isNotEmpty(users)) {
                return botProperties.getDomain() + "/sendMessage/" + users.getChatToken();
            }
        }

        // 生成链接
        String token = RandomStringUtils.randomAlphanumeric(16);
        Users entity = new Users();
        entity.setChatId(chatId);
        entity.setChatToken(token);
        usersRepository.save(entity);
        return botProperties.getDomain() + "/sendMessage/" + token;
    }

    /**
     * 删除链接
     *
     * @param chatId 用户id
     */
    public void delete(Long chatId) {
        Optional<Users> optionalUsers = usersRepository.findById(chatId);
        Users users;
        if (optionalUsers.isPresent()) {
            users = optionalUsers.get();
            usersRepository.delete(users);
        }
    }
}
