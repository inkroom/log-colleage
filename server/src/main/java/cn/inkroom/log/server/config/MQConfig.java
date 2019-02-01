package cn.inkroom.log.server.config;

import cn.inkroom.log.mq.MessageCenter;
import cn.inkroom.log.mq.MessageFactory;
import cn.inkroom.log.mq.MessageListener;
import cn.inkroom.log.mq.MessageSender;
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
import java.util.Properties;
import java.util.function.BiConsumer;

/**
 * @author 墨盒
 * @date 18-12-10
 */
@Configuration
public class MQConfig {

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Bean
    public MessageFactory factory() throws Exception {
        String type = PropertiesHandler.getProperty("mq.class");

//        构造mq属性
        Properties mqProperties = new Properties();
        PropertiesHandler.getProperties().forEach((key, value) -> {
            if (key.toString().startsWith("mq."))
                mqProperties.put(key.toString().replace("mq.", ""), value);
        });

        try {
            Class mqClass = Class.forName(type);

            Object factory = mqClass.newInstance();
            if (factory instanceof MessageFactory) {
                logger.debug("注入MessageFactory class={}",factory.getClass());
                ((MessageFactory) factory).init(mqProperties);
                return ((MessageFactory) factory);


            } else {
                throw new IllegalArgumentException("错误的类型，" + type);
            }

        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException("不存在的类型，" + type);
        }

    }

    //    /**
//     * 对数据进行包装
//     *
//     * @param listener 监听器
//     * @param center   注册器
//     * @return
//     */
//    @Bean
//    public Object listener(MessageListener listener, MessageCenter center) {
//
//        String channel = PropertiesHandler.getProperty("mq.channel.log");
//        logger.info("注册消息中间件监听，channel={}，类型=queues", channel);
//        center.addListener(listener, channel, false);
//        return null;
//    }
    @Bean
    public MessageSender sender(MessageFactory factory) {
        try {
            MessageSender sender = factory.createSender();
            logger.debug("注入MessageSender class={}", sender.getClass());
            return sender;
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Bean
    public MessageCenter center(MessageFactory factory) {
        try {
            MessageCenter center = factory.createCenter();
            logger.debug("注入MessageCenter class={}", center.getClass());
            return center;
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

}
