package cn.inkroom.log.mq.active;

import cn.inkroom.log.mq.MessageCenter;
import cn.inkroom.log.mq.MessageFactory;
import cn.inkroom.log.mq.MessageListener;
import cn.inkroom.log.mq.MessageSender;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * @author 墨盒
 * @date 19-2-10
 */
public class AllTest {

    private Logger logger = LoggerFactory.getLogger(getClass());


    private boolean isReceive = true;

    /**
     * 直接全部测试完
     * 由于多线程，，并不能完全测试，尤其是接收消息
     */
    @org.junit.Test
    public void test() throws Exception {

        Properties properties = new Properties();
        /*
        mq.class=cn.inkroom.log.mq.active.ActiveMessageFactory

# 以下为activeMq的注册信息
mq.username=system
mq.password=manager
mq.url=tcp://123.207.13.252:61616
         */
        properties.put("class", "cn.inkroom.log.mq.active.ActiveMessageFactory");
        properties.put("username", "system");
        properties.put("password", "manager");
        properties.put("url", "tcp://123.207.13.252:61616");


        MessageFactory factory = new ActiveMessageFactory();

        factory.init(properties);

        MessageCenter center = factory.createCenter();

        center.addListener(new TestListener(), "test", false);

        MessageSender sender = factory.createSender();
        sender.send("测试消息", "test", false);
        // 暂停，等待测试消息接收
        Thread.sleep(2000L);

        Assert.assertFalse("无法接收到消息", isReceive);
    }

    class TestListener implements MessageListener {

        @Override
        public boolean onMessage(String message, String channel) {
            logger.debug("收到消息={}", message);
            isReceive = false;
            return true;
        }
    }
}

