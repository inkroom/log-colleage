package cn.inkroom.log.mq;

/**
 * @author 墨盒
 * @Date 18-11-5
 */
public interface MessageCenter {
    void addListener(MessageListener listener,String channel,boolean topic);
}