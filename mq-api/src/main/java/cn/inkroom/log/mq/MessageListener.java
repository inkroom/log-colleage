package cn.inkroom.log.mq;

/**
 * @author 墨盒
 * @Date 18-11-5
 */
public interface MessageListener {

    void onMessage(String message, String channel);
}
