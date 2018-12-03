package cn.inkroom.log.mq.active;

import cn.inkroom.log.mq.MessageCenter;
import cn.inkroom.log.mq.MessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

/**
 * @author 墨盒
 * @Date 18-11-5
 */
public class ActiveMessageCenter implements MessageCenter {

    private Logger logger = LoggerFactory.getLogger(getClass());


    private Session session;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public void addListener(MessageListener listener, String channel, boolean topic) {

        try {
            MessageConsumer consumer = null;
            Destination destination = null;
            if (topic) {
                destination = session.createTopic(channel);
            } else {
                destination = session.createQueue(channel);
            }
            consumer = session.createConsumer(destination);

            if (listener instanceof ActiveMessageListener) {
                ActiveMessageListener activeMessageListener = (ActiveMessageListener) listener;

                activeMessageListener.setConsumer(consumer);
                activeMessageListener.setChannel(channel);

                new Thread(activeMessageListener).start();

            }
        } catch (JMSException e) {
            logger.warn(e.getMessage());
        }

    }
}
