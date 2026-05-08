package com.yun.yunpicturebackend.model.vo;

import cn.hutool.json.JSONUtil;
import com.yun.yunpicturebackend.common.PageRequest;
import com.yun.yunpicturebackend.model.entity.Picture;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class PictureVO implements Serializable {
    /**
     * id
     */
    private Long id;
    /**
     * 图片url
     */

    private String url;
    /**
     * 缩略图url
     */
    private String thumbnailUrl;
    /**
     * 图片名称
     */
    private String name;
    /**
     * 图片简介
     */
    private String introduction;
    /**
     * 图片标签
     */
    private List<String> tags;
    /**
     * 图片分类
     */
    private String category;
    /**
     * 图片大小
     */
    private Long picSize;
    /**
     * 图片宽
     */
    private Integer picWidth;
    /**
     * 图片高
     */
    private Integer picHeight;
    /**
     * 图片比例
     */
    private Double picScale;
    /**
     * 图片格式
     */
    private String picFormat;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 空间id
     */
    private Long spaceId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date editTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 用户信息
     */
    private UserVO user;
    /**
     * 图片颜色
     */
    private String picColor;

    /**
     * 权限列表
     */
    private List<String> permissionList = new ArrayList<>();

    private static final long serialVersionUID = 1L;

    public static Picture voToObj(PictureVO pictureVO) {
        if (pictureVO == null) {
            return null;
        }
        Picture picture = new Picture();
        BeanUtils.copyProperties(pictureVO, picture);
        picture.setTags(JSONUtil.toJsonStr(pictureVO.getTags()));
        return picture;
    }

    public static PictureVO objToVo(Picture picture) {
        if (picture == null) {
            return null;
        }
        PictureVO pictureVO = new PictureVO();
        BeanUtils.copyProperties(picture, pictureVO);

        pictureVO.setTags(JSONUtil.toList(picture.getTags(), String.class));
        return pictureVO;
    }


}
