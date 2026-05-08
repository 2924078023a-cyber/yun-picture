package com.yun.yunpicturebackend.api.imagesearch;

import com.yun.yunpicturebackend.api.imagesearch.model.ImageSearchResult;
import com.yun.yunpicturebackend.api.imagesearch.sub.GetImageFirstUrlApi;
import com.yun.yunpicturebackend.api.imagesearch.sub.GetImageListApi;
import com.yun.yunpicturebackend.api.imagesearch.sub.GetImagePageUrlApi;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class ImageSearchApiFacade {
    /**
     * 搜索图片
     *
     * @param imageUrl 图片 URL
     * @return 图片搜索结果列表
     */
    public static List<ImageSearchResult> searchImage(String imageUrl) {
        String imagePageUrl = GetImagePageUrlApi.getImagePageUrl(imageUrl);
        String imageFirstUrl = GetImageFirstUrlApi.getImageFirstUrl(imagePageUrl);
        List<ImageSearchResult> imageList = GetImageListApi.getImageList(imageFirstUrl);
        return imageList;
    }

    public static void main(String[] args) {

        String imageUrl = "https://www.codefather.cn/logo.png";
        List<ImageSearchResult> resultList = searchImage(imageUrl);
        System.out.println("结果列表" + resultList);
    }
}
