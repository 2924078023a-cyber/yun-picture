package com.yun.yunpicturebackend.manager.websocket.model;

import com.yun.yunpicturebackend.model.vo.UserVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PictureEditResponseMessage {
    private String type;


    private String message;


    private String editAction;


    private UserVO user;
}
