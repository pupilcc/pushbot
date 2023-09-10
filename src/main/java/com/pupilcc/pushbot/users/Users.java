package com.pupilcc.pushbot.users;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户实体类
 *
 * @author pupilcc
 */
@Entity
@Data
@NoArgsConstructor
public class Users {
    @Id
    private Long chatId;
    private String chatToken;
}
