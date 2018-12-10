package cn.inkroom.log.server.config;

import cn.inkroom.log.mq.MessageCenter;
import cn.inkroom.log.mq.MessageListener;
import cn.inkroom.log.mq.active.ActiveMessageCenter;
import cn.inkroom.log.server.handler.PropertiesHandler;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.jms.Connection;
import javax.jms.Session;

/**
 * @author 墨盒
 * @Date 18-12-10
 */
@Configuration
public class MQConfig {

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Bean
    public MessageCenter center() throws Exception {
        String type = PropertiesHandler.getProperty("mq.type");
        switch (type) {
            case "ActiveMq":
                //构造activeMq的环境
                //创建一个链接工厂
                ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(PropertiesHandler.getProperty("mq.username"),
                        PropertiesHandler.getProperty("mq.password"),
                        PropertiesHandler.getProperty("mq.url"));
                //从工厂中创建一个链接
                Connection connection = connectionFactory.createConnection();
                //开启链接
                connection.start();
                //创建一个事务（这里通过参数可以设置事务的级别）
                Session session = connection.createSession(true, Session.SESSION_TRANSACTED);
                return new ActiveMessageCenter(session);
        }
        throw new IllegalArgumentException("非法的类型,mq.type=" + type);
    }

    /**
     * 对数据进行包装
     *
     * @param listener 监听器
     * @param center   注册器
     * @return
     */
    @Bean
    public Object listener(MessageListener listener, MessageCenter center) {

        String channel = PropertiesHandler.getProperty("mq.channel");
        logger.info("注册消息中间件监听，channel={}，类型=queues", channel);
        center.addListener(listener, channel, false);
        return null;
    }

}
