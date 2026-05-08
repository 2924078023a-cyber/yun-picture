package com.yun.yunpicturebackend.model.dto.space;

import lombok.Data;

import java.io.Serializable;
@Data
public class SpaceUpdateRequest implements Serializable {

    /**
     * 空间id
     */
    private Long id;

    /**
     * 空间名
     */
    private String spaceName;

    /**
     * 空间等级
     */
    private Integer spaceLevel;

    /**
     * 空间大小
     */
    private Long maxSize;

    /**
     * 文件数量限制
     */
    private Long maxCount;

    private static final long serialVersionUID = 1L;

}
