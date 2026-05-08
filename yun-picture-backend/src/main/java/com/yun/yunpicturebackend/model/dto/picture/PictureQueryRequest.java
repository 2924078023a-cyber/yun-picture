package com.yun.yunpicturebackend.model.dto.picture;

import com.yun.yunpicturebackend.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class PictureQueryRequest extends PageRequest implements Serializable {
    /**
     * 图片ID
     */
    private Long id;
    /**
     * 图片名称
     */
    private String name;
    /**
     * 图片简介
     */
    private String introduction;
    /**
     * 图片分类
     */
    private String category;
    /**
     * 图片标签
     */
    private List<String> tags;
    /**
     * 图片大小
     */
    private Long picSize;
    /**
     * 图片宽度
     */
    private Integer picWidth;
    /**
     * 图片高度
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
     * 图片搜索文本
     */
    private String searchText;
    /**
     * 图片上传者
     */
    private Long userId;
    /**
     * 图片审核状态
     */
    private Integer reviewStatus;
    /**
     * 图片审核信息
     */
    private String reviewMessage;
    /**
     * 图片审核者
     */
    private Long reviewerId;
    /**
     * 审核时间
     */
    private Date reviewTime;
    /**
     * spaceID
     */
    private Long spaceId;
    /**
     * 是否只查询空间ID为null的图片
     */
    private boolean nullSpaceId;


    private Date startEditTime;


    private Date endEditTime;


    private static final long serialVersionUID = 1L;

}
