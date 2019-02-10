package cn.inkroom.log.mq;

/**
 * @author 墨盒
 * @date 18-11-5
 */
public interface MessageCenter {
    void addListener(MessageListener listener, String channel, boolean topic);

    /**
     * 移除监听器
     *
     * @param listener 监听器
     * @param channel  频道
     */
    void removeListener(MessageListener listener, String channel);
}
