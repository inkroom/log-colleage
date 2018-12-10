package cn.inkroom.log.server.server;

import cn.inkroom.log.mq.MessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 负责接收日志消息
 *
 * @author 墨盒
 * @Date 18-12-10
 */
@Service
public class MessageService implements MessageListener {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onMessage(String message, String channel) {

        logger.debug("接受到消息={}", message);

    }
}
