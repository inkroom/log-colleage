package cn.inkroom.log.server.server;

import cn.inkroom.log.model.LogMsg;
import cn.inkroom.log.mq.MessageListener;
import cn.inkroom.log.server.dao.LogDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private LogDao dao;

    @Override
    public boolean onMessage(String message, String channel) {

        logger.debug("接受到消息={}", message);

        //转换成LogMsg
        try {
            LogMsg msg = LogMsg.getInstanceFromJson(message);
            logger.debug("转换之后的json={}", msg);
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
