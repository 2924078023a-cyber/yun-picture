package com.yun.yunpicturebackend.model.dto.picture;

import lombok.Data;

import java.io.Serializable;

/**
 * 以图搜图请求
 *
 */
@Data
public class SearchPictureByPictureRequest implements Serializable {


    private Long pictureId;

    private static final long serialVersionUID = 1L;
}
