package cn.inkroom.log.web.service.log.impl;

import cn.inkroom.log.model.LogMsg;
import cn.inkroom.log.web.service.StatisticsService;
import cn.inkroom.log.web.service.log.AbstractLogReceiverService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * 统计服务
 *
 * @author 墨盒
 * @date 19-2-10
 */
@Service
public class StatisticsLogServer extends AbstractLogReceiverService {

    @Autowired
    private StatisticsService server;

    @Override
    public boolean onMessage(String message, String channel) {

        LogMsg msg = JSON.parseObject(message, LogMsg.class);
        boolean res = server.updateCount(msg.getTag(), msg.getIp(), msg.getLevel(), 1);
        if (!res) {
            logger.warn("数据统计入库失败,消息={}", message);
            return false;
        }
        return true;
    }


}
