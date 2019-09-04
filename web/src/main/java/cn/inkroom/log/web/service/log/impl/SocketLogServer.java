/**
 * Copyright © 2019 inkbox (enpassPixiv@protonmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.inkroom.log.web.service.log.impl;

import cn.inkroom.log.web.service.log.AbstractLogReceiverService;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * 日志服务，用于实时日志
 *
 * @author 墨盒
 * @date 19-2-10
 */
@Service
public class SocketLogServer extends AbstractLogReceiverService implements WebSocketHandler {


    private List<WebSocketSession> socketSessions;


    public SocketLogServer() {
        socketSessions = new LinkedList<>();
    }

    @Override
    public boolean onMessage(String message, String channel) {
        logger.debug("收到日志消息={}", message);
        //发送日志
        socketSessions.forEach(webSocketSession -> {
            WebSocketMessage<String> socketMessage = new TextMessage(message);
            try {
                webSocketSession.sendMessage(socketMessage);
            } catch (IOException e) {
                logger.warn("websocket发送消息失败", e);
            }
        });
        return false;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        logger.debug("socket 链接");
        socketSessions.add(webSocketSession);
        if (socketSessions.size() == 1) {//第一个socket链接，此时才订阅频道
            bind();
        }
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        logger.debug("socket 发送错误");
        webSocketSession.close();
        socketSessions.remove(webSocketSession);
        if (socketSessions.size() == 0) {//没有人关心，此时移除订阅
            unbind();
//            center.removeListener(this, channel);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        logger.debug("socket 关闭");
        socketSessions.remove(webSocketSession);
        if (socketSessions.size() == 0) {//没有人关心，此时移除订阅
            unbind();
        }
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        logger.debug("收到socket client消息={}", webSocketMessage.getPayload());
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {


    }
}
