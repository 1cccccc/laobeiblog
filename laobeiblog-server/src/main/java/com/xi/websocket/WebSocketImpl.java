package com.xi.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class WebSocketImpl implements WebSocket{
    private final AtomicInteger count=new AtomicInteger(0);
    private final CopyOnWriteArraySet<WebSocketSession> sessions=new CopyOnWriteArraySet<>();

    //连接时
    @Override
    public void handleOpen(WebSocketSession session) {
        sessions.add(session);
        count.incrementAndGet();
        log.info("有一个新的连接，当前连接数：{}",count);
    }

    //断开连接
    @Override
    public void handleClose(WebSocketSession session) {
        sessions.remove(session);
        count.decrementAndGet();
        log.info("有一个连接断开，当前连接数：{}",count);
    }

    //接收到消息
    @Override
    public void handleMessage(WebSocketSession session, String message) {
        log.info("接收到消息：{}",message);
    }

    //发送消息
    @Override
    public void sendMessage(WebSocketSession session, String message) throws IOException {
        this.sendMessage(session,new TextMessage(message));
    }

    //发送消息
    @Override
    public void sendMessage(String userId, String message) throws IOException {
        this.sendMessage(userId,new TextMessage(message));
    }

    //发送消息
    @Override
    public void sendMessage(String userId, TextMessage message) throws IOException {
        Optional<WebSocketSession> userSession = sessions.stream().filter(session -> {
            if (!session.isOpen()) {
                return false;
            }
            Map<String, Object> attributes = session.getAttributes();
            if (!attributes.containsKey("uid")) {
                return false;
            }
            String uid = (String) attributes.get("uid");
            return uid.equals(userId);
        }).findFirst();
        if (userSession.isPresent()) {
            userSession.get().sendMessage(message);
        }
    }

    //发送消息
    @Override
    public void sendMessage(WebSocketSession session, TextMessage message) throws IOException {
        session.sendMessage(message);
    }

    //广播
    @Override
    public void broadCast(String message) throws IOException {
        for (WebSocketSession session : sessions) {
            if (!session.isOpen()) {
                continue;
            }
            this.sendMessage(session, message);
        }
    }

    //广播
    @Override
    public void broadCast(TextMessage message) throws IOException {
        for (WebSocketSession session : sessions) {
            if (!session.isOpen()) {
                continue;
            }
            session.sendMessage(message);
        }
    }

    //异常处理
    @Override
    public void handleError(WebSocketSession session, Throwable error) {
        log.error("websocket error：{}，session id：{}", error.getMessage(), session.getId());
        log.error("", error);
    }

    //获得所有会话
    @Override
    public Set<WebSocketSession> getSessions() {
        return sessions;
    }

    //获得连接数
    @Override
    public int getConnectionCount() {
        return count.get();
    }
}
