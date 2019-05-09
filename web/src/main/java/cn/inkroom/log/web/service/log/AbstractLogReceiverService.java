package cn.inkroom.log.web.service.log;

import cn.inkroom.log.mq.MessageCenter;
import cn.inkroom.log.mq.MessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * 监听日志基类
 * <p>默认自动注册监听，覆盖after即可去除</p>
 *
 * @author 墨盒
 * @date 19-5-9
 */
public abstract class AbstractLogReceiverService implements MessageListener, InitializingBean {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MessageCenter center;
    @Value("${mq.channel.topic.log}")
    private String channel;


    @Override
    public void afterPropertiesSet() throws Exception {
      bind();
    }

    protected void bind(){
        center.addListener(this, channel, true);
    }

    protected void unbind() {
        center.removeListener(this, channel);
    }
}
