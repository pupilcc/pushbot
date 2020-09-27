package com.pupilcc.pushbot.users;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Users CURD
 * @author pupilcc
 */
public interface UsersRepository extends JpaRepository<Users, Long> {
    /**
     * 通过 token 查找用户
     * @param token 创建链接时的随机字符串
     * @return 用户
     */
    Users findByChatToken(String token);
}
