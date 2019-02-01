package cn.inkroom.log.mq;

/**
 * @author 墨盒
 * @date 18-11-5
 */
public interface MessageListener {
    /**
     * 处理接受结果
     * @param message 消息
     * @param channel 频道
     * @return 如果处理成功返回true，建议此时mq进行确认操作
     */
    boolean onMessage(String message, String channel);
}
