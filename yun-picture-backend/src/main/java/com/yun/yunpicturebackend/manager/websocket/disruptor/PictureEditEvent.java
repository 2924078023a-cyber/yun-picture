package com.yun.yunpicturebackend.manager.websocket.disruptor;

import com.yun.yunpicturebackend.manager.websocket.model.PictureEditRequestMessage;
import com.yun.yunpicturebackend.model.entity.User;
import lombok.Data;
import org.springframework.web.socket.WebSocketSession;

@Data
public class PictureEditEvent {

    private PictureEditRequestMessage pictureEditRequestMessage;


    private WebSocketSession session;


    private User user;


    private Long pictureId;
}
