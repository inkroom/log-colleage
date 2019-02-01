package cn.inkroom.log.mq;

/**
 * @author 墨盒
 * @date 18-11-5
 */
public interface MessageSender {

    boolean send(String message, String channel,boolean topic);


}
