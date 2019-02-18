package cn.inkroom.log.mq.active;

import cn.inkroom.log.mq.MessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

/**
 * @author 墨盒
 * @date 18-11-5
 */
public class ActiveMessageListener implements Runnable {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private MessageListener listener;

    private MessageConsumer consumer;
    private String channel;

    private boolean stop = false;

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public String getChannel() {
        return channel;
    }

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
        while (!stop) {
            try {
                Message message = consumer.receive();
                if (message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    if (listener.onMessage(textMessage.getText(), this.channel)) {
                        message.acknowledge();
                    }
                }
            } catch (JMSException e) {
                logger.warn(e.getMessage());
                break;
            }
        }
        try {
            consumer.close();
        } catch (JMSException e) {
            logger.warn("关闭订阅错误", e);
        }
    }

}
