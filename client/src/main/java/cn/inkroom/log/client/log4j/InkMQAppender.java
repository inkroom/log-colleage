package cn.inkroom.log.client.log4j;

import cn.inkroom.log.model.LogMsg;
import cn.inkroom.log.mq.MessageSender;
import cn.inkroom.log.mq.active.ActiveMessageSender;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.AppenderSkeleton;
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

/**
 * @author 墨盒
 * @date 18-11-5
 */
public class InkMQAppender extends AppenderSkeleton {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private MessageSender sender;


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
        }
    }

    @Override
    protected void append(LoggingEvent event) {
        String msg = this.layout.format(event);
        LogMsg logMsg = new LogMsg();
        logMsg.setMsg(msg);
        logMsg.setTime(new Date(event.getTimeStamp()));
        logMsg.setIp(localIp);
        logMsg.setTag(tag);
//TODO 18-11-20 注意处理activeMq断掉的情况
        sender.send(logMsg.toString(), channel, false);
    }

    @Override
    public void close() {

    }

    @Override
    public boolean requiresLayout() {
        return true;
    }
}
