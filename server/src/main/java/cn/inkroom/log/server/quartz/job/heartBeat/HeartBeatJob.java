package cn.inkroom.log.server.quartz.job.heartBeat;

import cn.inkroom.log.model.Server;
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

import javax.annotation.Resource;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

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
    @Value("${socket.port}")
    private int port;
    @Resource(name = "localServer")
    private Server server;
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
//        try {

        server.setLast(new Date());
        server.setRun(System.currentTimeMillis() - server.getStart());

//            JSONObject object = new JSONObject();
//            object.put("ip", InetAddress.getLocalHost().getHostAddress());
//            object.put("port", port);
        logger.debug("发送心跳包={}", server.toString());
        sender.send(server.toString(), heartBeatChannel, true);
//        } catch (UnknownHostException e) {
//            logger.warn("无法获取本地ip地址,{}", e);
//        }
    }
}
