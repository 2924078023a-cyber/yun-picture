package com.yun.yunpicturebackend.model.dto.user;


import lombok.Data;

import java.io.Serializable;

@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = -4198878660435644182L;
    private String userAccount;

    private String userPassword;

    private String checkPassword;
}
