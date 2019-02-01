package cn.inkroom.log.server.quartz.job.heartBeat;

import cn.inkroom.log.mq.MessageSender;
import com.alibaba.fastjson.JSONObject;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 心跳包定时任务
 *
 * @author 墨盒
 * @date 19-2-1
 */
//@Service
public class HeartBeatJob extends QuartzJobBean {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private MessageSender sender;
    @Value("${mq.channel.heartbeat}")
    private String heartBeatChannel;
//    @Autowired
//    public HeartBeatJob(SchedulerManager manager) {
//        logger.info("");
//    }

    public HeartBeatJob() {
        logger.debug("心跳包被创建");
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        //发送心跳包
        try {
            JSONObject object = new JSONObject();
            object.put("ip", InetAddress.getLocalHost().getHostAddress());
            logger.debug("发送心跳包={}", object.toJSONString());
            sender.send(object.toJSONString(), heartBeatChannel, true);
        } catch (UnknownHostException e) {
            logger.warn("无法获取本地ip地址,{}", e);
        }
    }
}
