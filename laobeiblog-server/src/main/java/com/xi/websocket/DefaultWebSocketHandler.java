package com.xi.websocket;

import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.socket.*;

public class DefaultWebSocketHandler implements WebSocketHandler {
    @Resource
    private WebSocket webSocket;


    /**
     * 建立连接
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(@NotNull WebSocketSession session) throws Exception {
        webSocket.handleOpen(session);
    }

    /**
     * 接收消息
     * @param session
     * @param message 消息
     * @throws Exception
     */
    @Override
    public void handleMessage(@NotNull WebSocketSession session, @NotNull WebSocketMessage<?> message) throws Exception {
        if (message instanceof TextMessage){
            TextMessage textMessage=(TextMessage) message;
            webSocket.handleMessage(session,textMessage.getPayload());
        }
    }

    /**
     * 发生错误
     * @param session
     * @param exception 消息
     * @throws Exception
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        webSocket.handleError(session,exception);
    }

    /**
     * 关闭连接
     * @param session
     * @param closeStatus 关闭状态
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(@NotNull WebSocketSession session,@NotNull CloseStatus closeStatus) throws Exception {
        webSocket.handleClose(session);
    }

    /**
     * 是否支持发送部分消息
     * @return
     */
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
