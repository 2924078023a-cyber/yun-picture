    package com.yun.yunpicturebackend.api.imagesearch.sub;

    import cn.hutool.core.util.URLUtil;
    import cn.hutool.http.HttpRequest;
    import cn.hutool.http.HttpResponse;
    import cn.hutool.http.HttpStatus;
    import cn.hutool.json.JSONUtil;
    import com.yun.yunpicturebackend.exception.BusinessException;
    import com.yun.yunpicturebackend.exception.ErrorCode;
    import lombok.extern.slf4j.Slf4j;

    import java.io.File;
    import java.nio.charset.StandardCharsets;
    import java.util.HashMap;
    import java.util.Map;

    @Slf4j
    public class GetImagePageUrlApi {

        public static String getImagePageUrl(String imageUrl) {

            Map<String, Object> formData = new HashMap<>();
            formData.put("image", imageUrl);
            formData.put("tn", "pc");
            formData.put("from", "pc");
            formData.put("image_source", "PC_UPLOAD_URL");

            long uptime = System.currentTimeMillis();

            String url = "https://graph.baidu.com/upload?uptime=" + uptime;

            try {

                HttpResponse response = HttpRequest.post(url)
                        .header("acs-token", "123456")
                        .form(formData)
                        .timeout(5000)
                        .execute();

                if (HttpStatus.HTTP_OK != response.getStatus()) {
                    throw new BusinessException(ErrorCode.OPERATION_ERROR, "接口调用失败");
                }

                String responseBody = response.body();
                Map<String, Object> result = JSONUtil.toBean(responseBody, Map.class);


                if (result == null || !Integer.valueOf(0).equals(result.get("status"))) {
                    throw new BusinessException(ErrorCode.OPERATION_ERROR, "接口调用失败");
                }
                Map<String, Object> data = (Map<String, Object>) result.get("data");
                String rawUrl = (String) data.get("url");

                String searchResultUrl = URLUtil.decode(rawUrl, StandardCharsets.UTF_8);

                if (searchResultUrl == null) {
                    throw new BusinessException(ErrorCode.OPERATION_ERROR, "未返回有效结果");
                }
                return searchResultUrl;
            } catch (Exception e) {
                log.error("搜索失败", e);
                throw new BusinessException(ErrorCode.OPERATION_ERROR, "搜索失败");
            }
        }

    }
