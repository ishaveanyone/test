package com.dist.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@SpringBootApplication
public class WebsocketApplication  implements WebSocketConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(WebsocketApplication.class, args);
    }
    //Socket消息模版类，用来向客户端推送消息
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    //用请求的方式模拟主动推送消息
    @GetMapping("notice")
    public String notice(String name) {
        //这里定义了订阅消息的路径是"/queue/notice"，客户端请求的路径则为："/user/queue/notice"
        simpMessagingTemplate.convertAndSendToUser(name, "/queue/notice", "当前时间是：" + new Date());
        return "已发送";
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {

    }
}
