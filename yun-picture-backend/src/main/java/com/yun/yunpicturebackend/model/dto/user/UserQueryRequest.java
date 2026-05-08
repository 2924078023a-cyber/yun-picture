package com.yun.yunpicturebackend.model.dto.user;

import com.yun.yunpicturebackend.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserQueryRequest extends PageRequest implements Serializable {

    private Long id;

    private String userName;

    private String userAccount;


    private String userProfile;

    private String userRole;

    private Integer reviewStatus;

    private String reviewMessage;

    private Long reviewId;


    private String sortOrder = "descend";

    private static final long seriaVersionID = 1L;
}
