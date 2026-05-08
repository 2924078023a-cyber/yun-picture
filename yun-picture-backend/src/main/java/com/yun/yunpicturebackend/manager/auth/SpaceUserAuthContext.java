package com.yun.yunpicturebackend.manager.auth;

import com.yun.yunpicturebackend.model.entity.Picture;
import com.yun.yunpicturebackend.model.entity.Space;
import com.yun.yunpicturebackend.model.entity.SpaceUser;
import lombok.Data;

@Data
public class SpaceUserAuthContext {

    /**
     * 临时参数，不同请求对于的 id 可能不同
     *
     */
    private Long id;

    /**
     * 图片 id
     *
     */
    private Long pictureId;

    /**
     * 空间 id
     *
     */
    private Long spaceId;

    /**
     * 空间用户 id
     *
     */
    private Long spaceUserId;

    /**
     * 图片信息
     *
     */
    private Picture picture;

    /**
     * 空间信息
     *
     */
    private Space space;

    /**
     * 空间用户信息
     *
     */
    private SpaceUser spaceUser;
}
