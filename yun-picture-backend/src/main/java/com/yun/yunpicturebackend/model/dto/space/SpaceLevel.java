package com.yun.yunpicturebackend.model.dto.space;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SpaceLevel {

    /**
     * 等级值
     */
    private int value;

    /**
     * 等级描述
     */
    private String text;

    /**
     * 最大文件数量
     */
    private long maxCount;

    /**
     * 最大文件大小
     */
    private long maxSize;
}
