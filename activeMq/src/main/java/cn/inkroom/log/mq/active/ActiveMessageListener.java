package cn.inkroom.log.mq.active;

import cn.inkroom.log.mq.MessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

/**
 * @author 墨盒
 * @Date 18-11-5
 */
public class ActiveMessageListener implements Runnable {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private MessageListener listener;

    private MessageConsumer consumer;
    private String channel;

    public ActiveMessageListener(MessageListener listener, MessageConsumer consumer, String channel) {
        this.consumer = consumer;
        this.channel = channel;
        this.listener = listener;
    }

    public void setConsumer(MessageConsumer consumer) {
        this.consumer = consumer;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Message message = consumer.receive();
                if (message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    listener.onMessage(textMessage.getText(), this.channel);
                }
            } catch (JMSException e) {
                logger.warn(e.getMessage());
            }
        }
    }

}
