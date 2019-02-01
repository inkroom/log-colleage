package cn.inkroom.log.mq.active;

import cn.inkroom.log.mq.MessageCenter;
import cn.inkroom.log.mq.MessageFactory;
import cn.inkroom.log.mq.MessageSender;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.Session;
import java.util.Properties;

/**
 * @author 墨盒
 * @date 19-2-1
 */
public class ActiveMessageFactory implements MessageFactory {


    private Connection connection;

    public void init(Properties properties) throws Exception {
        //构造activeMq的环境
        //创建一个链接工厂
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(properties.getProperty("username"),
                properties.getProperty("password"),
                properties.getProperty("url"));
        //从工厂中创建一个链接
        Connection connection = connectionFactory.createConnection();

        this.connection = connection;
        //开启链接
        connection.start();
        //创建一个事务（这里通过参数可以设置事务的级别）
//        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
//        return new ActiveMessageCenter(session);
    }


    @Override
    public MessageCenter createCenter() throws Exception {
        return new ActiveMessageCenter(connection.createSession(false, Session.CLIENT_ACKNOWLEDGE));
    }

    @Override
    public MessageSender createSender() throws Exception {
        return new ActiveMessageSender(connection.createSession(false, Session.CLIENT_ACKNOWLEDGE));
    }
}
