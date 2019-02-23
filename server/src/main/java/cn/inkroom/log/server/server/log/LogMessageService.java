package cn.inkroom.log.server.server.log;

import cn.inkroom.log.model.LogMsg;
import cn.inkroom.log.mq.MessageCenter;
import cn.inkroom.log.mq.MessageListener;
import cn.inkroom.log.mq.MessageSender;
import cn.inkroom.log.server.dao.LogDao;
import cn.inkroom.log.server.handler.PropertiesHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 负责接收日志消息
 *
 * @author 墨盒
 * @date 18-12-10
 */
@Service
public class LogMessageService implements MessageListener {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private LogDao dao;
    //    将日志转化成topic消息
    @Value("${mq.channel.topic.log}")
    private String topicChannel;
    private MessageSender sender;

    @Autowired
    public LogMessageService(MessageCenter center, MessageSender sender) {
        this.sender = sender;
        logger.info("注册 - 日志 - 消息中间件监听，channel={}，类型=queues", PropertiesHandler.getProperty("mq.channel.queue.log"));
        center.addListener(this, PropertiesHandler.getProperty("mq.channel.queue.log"), false);
    }

    @Override
    public boolean onMessage(String message, String channel) {


        logger.debug("接受到消息={}", message);

        //转换成LogMsg
        try {
            LogMsg msg = LogMsg.getInstanceFromJson(message);

//            logger.debug("转换之后的json={}", msg);
            //发送出去
            sender.send(message, topicChannel, true);
            //执行入库操作
            if (dao.insert(msg) != 1) {
                logger.error("日志入库失败");
                return false;
            }

        } catch (Exception e) {
            logger.warn("错误的json数据={}，不能转换成{}", message, LogMsg.class);
            logger.error(e.getMessage(), e);
//            e.printStackTrace();
            return false;
        }
        return true;

    }
}
