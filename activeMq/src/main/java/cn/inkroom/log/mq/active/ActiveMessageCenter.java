package cn.inkroom.log.mq.active;

import cn.inkroom.log.mq.MessageCenter;
import cn.inkroom.log.mq.MessageListener;
import cn.inkroom.log.mq.MessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;
import java.util.Hashtable;

/**
 * @author 墨盒
 * @date 18-11-5
 */
public class ActiveMessageCenter implements MessageCenter {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private Session session;

    private Hashtable<MessageListener, ActiveMessageListener> listeners;

    public ActiveMessageCenter(Session session) {
        this.session = session;
        listeners = new Hashtable<>();
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

//            if (listener instanceof ActiveMessageListener) {
            ActiveMessageListener activeMessageListener = new ActiveMessageListener(listener, consumer, channel);

            listeners.put(listener, activeMessageListener);
            new Thread(activeMessageListener).start();

//            }
        } catch (JMSException e) {
            logger.warn(e.getMessage());
        }

    }

    @Override
    public void removeListener(MessageListener listener, String channel) {
        ActiveMessageListener lis = listeners.get(listener);
        if (lis != null && lis.getChannel().equals(channel)) {
            lis.setStop(true);
            listeners.remove(listener);
        }
    }
}
