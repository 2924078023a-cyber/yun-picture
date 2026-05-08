package com.yun.yunpicturebackend.model.dto.user;

import lombok.Data;

@Data
public class UserAddRequest {

    private String userName;

    private String userAccount;

    private String userAvatar;

    private String userProfile;

    private String userRole;

    private static final long seriaVersionID = 1L;
}
