/**
 * Copyright © 2019 inkbox (enpassPixiv@protonmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.inkroom.log.server.config;

import cn.inkroom.log.model.Server;
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
import java.net.InetAddress;
import java.util.Calendar;
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
    @Bean
    public Server localServer() throws Exception{

        Server server = new Server();
        server.setIp(InetAddress.getLocalHost().getHostAddress());
        server.setStart(System.currentTimeMillis());
        server.setStatus(true);
        //TODO 19-2-9 修改文件下载端口注入方式
        server.setFilePort(9876);

        return server;
    }

}
