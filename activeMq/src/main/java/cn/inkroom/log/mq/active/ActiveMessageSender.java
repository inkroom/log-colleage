package cn.inkroom.log.mq.active;

import cn.inkroom.log.mq.MessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;
import java.io.Closeable;
import java.io.IOException;

/**
 * @author 墨盒
 * @Date 18-11-5
 */
public class ActiveMessageSender implements MessageSender, Closeable {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private ThreadLocal<MessageProducer> local = new ThreadLocal<>();
    private Session session;

    public ActiveMessageSender(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public boolean send(String message, String channel, boolean topic) {
        try {
            MessageProducer producer = null;
            if (local.get() != null) {
                producer = local.get();
            } else {
                Destination destination = null;
                if (topic) {
                    destination = session.createTopic(channel);
                } else {
                    destination = session.createQueue(channel);
                }
                producer = session.createProducer(destination);
                local.set(producer);
            }
            producer.send(session.createTextMessage(message));
//            session.commit();
        } catch (JMSException e) {
            logger.warn(e.getMessage());
        }

        return true;
    }

    @Override
    public void close() throws IOException {
        try {
            session.close();
        } catch (JMSException e) {
            throw new IOException(e);
        }
    }
}
