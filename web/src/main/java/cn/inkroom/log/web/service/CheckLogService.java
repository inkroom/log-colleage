package cn.inkroom.log.web.service;

import cn.inkroom.log.model.LogMsg;
import cn.inkroom.log.mq.MessageCenter;
import cn.inkroom.log.mq.MessageListener;
import cn.inkroom.log.web.alert.MessageAlert;
import cn.inkroom.log.web.alert.MessageAlertFactory;
import cn.inkroom.log.web.bean.Alarm;
import cn.inkroom.log.web.check.CheckLog;
import cn.inkroom.log.web.check.CheckLogFactory;
import cn.inkroom.log.web.handler.PropertiesHandler;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author 墨盒
 * @date 19-5-6
 */
@Service
public class CheckLogService implements MessageListener, InitializingBean {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MessageCenter center;
    @Value("${mq.channel.topic.log}")
    private String channel;

    @Autowired
    private AlarmService service;

    private CheckLog checkLog;

    private MessageAlert alert;

    @Override
    public boolean onMessage(String message, String channel) {
        //TODO 19-5-6 需要考虑一下效率问题
        if (checkLog != null) {
            LogMsg logMsg = JSON.parseObject(message, LogMsg.class);
            String msg = checkLog.checkLog(logMsg);
            if (msg != null && alert != null) {
                if (alert.alert(msg)) {
                    // 入库
                    //TODO 报警信息入库

                    Alarm alarm = new Alarm();
                    alarm.setIp(logMsg.getIp());
                    alarm.setTag(logMsg.getTag());
                    alarm.setMessage(msg);
                    alarm.setStatus(0);
                    alarm.setCreatedAt(new Date());

                    service.insertAlarm(alarm);
                } else {
                    logger.warn("错误信息{},发送通知失败", msg);
                }
            }
        }
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        this.checkLog = CheckLogFactory.create(PropertiesHandler.getProperties());
        if (this.checkLog instanceof MessageAlert) {//允许 checkLog实现消息发送功能
            this.alert = ((MessageAlert) this.checkLog);
        } else
            this.alert = MessageAlertFactory.create(PropertiesHandler.getProperties());

        if (this.checkLog != null) {//只有配置了检测的才监听queue
            center.addListener(this, channel, true);
            if (this.alert == null) {
                logger.warn("没有配置报警方式，将无法正常接收报警信息");
            }

        }
    }
}
