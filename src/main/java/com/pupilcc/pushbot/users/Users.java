package com.pupilcc.pushbot.users;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 用户实体类
 *
 * @author pupilcc
 */
@Entity
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Long chatId;
    private String chatToken;

    public Users() {
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getChatToken() {
        return chatToken;
    }

    public void setChatToken(String chatToken) {
        this.chatToken = chatToken;
    }
}
