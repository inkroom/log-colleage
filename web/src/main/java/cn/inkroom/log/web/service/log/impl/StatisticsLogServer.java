package cn.inkroom.log.web.service.log.impl;

import cn.inkroom.log.model.LogMsg;
import cn.inkroom.log.web.service.StatisticsService;
import cn.inkroom.log.web.service.log.AbstractLogReceiverService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

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
    @Value("${statistic.interval}")
    private int interval;

    private AtomicLong lastTime = new AtomicLong(0);

    @Override
    public boolean onMessage(String message, String channel) {


        LogMsg msg = JSON.parseObject(message, LogMsg.class);

        long time = System.currentTimeMillis();
        if (time - lastTime.get() > interval) {//此时应该直接新建一条记录
            boolean res = server.addCount(msg.getTag(), msg.getIp(), msg.getLevel(), 1);
            if (!res) {
                logger.warn("数据统计入库失败,消息={}", message);
                return false;
            }
            lastTime.set(time);
            return true;
        } else {//更新之前的记录
            boolean res = server.updateCount(msg.getTag(), msg.getIp(), msg.getLevel(), 1);
            if (!res) {
                logger.warn("数据统计入库失败,消息={}", message);
                return false;
            }
            return true;
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.afterPropertiesSet();
        //将分钟转成毫秒来计算
        interval = interval * 60 * 1000;

    }
}
