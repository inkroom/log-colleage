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
package cn.inkroom.log.client.log4j;

import cn.inkroom.log.model.LogMsg;
import cn.inkroom.log.mq.MessageSender;
import cn.inkroom.log.mq.active.ActiveMessageSender;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.pattern.LogEvent;
import org.apache.log4j.spi.LoggingEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Session;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author 墨盒
 * @date 18-11-5
 */
public class InkMQAppender extends AppenderSkeleton {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private MessageSender sender;
    private Queue<LogMsg> queue = new LinkedBlockingDeque<>();

    private String username;
    private String password;

    private String channel;
    private String url;

    private String localIp;

    private String tag;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void activateOptions() {
        System.out.println("username=" + username + ", password=" + password);
        try {
            //创建一个链接工厂
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(username, password, url);
            //从工厂中创建一个链接
            Connection connection = connectionFactory.createConnection();
            //开启链接
            connection.start();
            //创建一个事务（这里通过参数可以设置事务的级别）
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            InetAddress addr = InetAddress.getLocalHost();
            localIp = addr.getHostAddress(); //获取本机ip

            sender = new ActiveMessageSender(session);
        } catch (JMSException | UnknownHostException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    @Override
    protected void append(LoggingEvent event) {
        LogMsg logMsg = analyzeLog(event);
        if (!queue.isEmpty()) {//有以往数据未发送
            queue.add(logMsg);
//                为了防止阻塞，将积攒的日志消息分多次发送
            for (int i = 0; i < 10 && !queue.isEmpty(); i++) {
                if (sender.send(queue.element().toString(), channel, false)) {
                    queue.poll();
                } else {//发送失败
                    return;
                }
            }
        }
        if (!sender.send(logMsg.toString(), channel, false)) {
            //发送失败，入队，等待下次发送日志机会
            queue.add(logMsg);
        }
    }

    private LogMsg analyzeLog(LoggingEvent event) {
        String msg = this.layout.format(event);
        LogMsg logMsg = new LogMsg();
        logMsg.setMsg(msg);
        logMsg.setTime(new Date(event.getTimeStamp()));
        logMsg.setIp(localIp);
        logMsg.setTag(tag);
        logMsg.setLevel(event.getLevel().toInt());
        return logMsg;
    }

    @Override
    public void close() {

    }

    @Override
    public boolean requiresLayout() {
        return true;
    }
}
