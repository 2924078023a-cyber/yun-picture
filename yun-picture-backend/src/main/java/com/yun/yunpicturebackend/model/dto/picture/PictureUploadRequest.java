package com.yun.yunpicturebackend.model.dto.picture;

import lombok.Data;

import java.io.Serializable;

@Data
public class PictureUploadRequest implements Serializable {
    /**
     * 图片id
     */
    private Long id;
    /**
     * 图片地址
     */
    private String fileUrl;
    /**
     * 图片名称
     */
    private String PicName;

    /**
     * 空间id
     */
    private Long spaceId;

    private static final long serialVersionUID = 1L;

}
