package cn.inkroom.log.mq;

import java.util.Properties;

/**
 * 抽象工厂
 * <p>用于生成MessageCenter</p>
 * 每一个mq实现类都必须提供该接口的实例，必须提供无参构造方法<br>
 *
 * @author 磨合
 * @date 2019-2-1
 */
public interface MessageFactory {
    /**
     * 初始化属性
     * <p>该方法用于初始化属性，在create方法前被调用</p>
     *
     * @param properties 属性
     * @throws Exception
     */
    void init(Properties properties) throws Exception;

    /**
     * 构造center
     *
     * @param properties 无前缀属性
     * @return center
     */
    MessageCenter createCenter() throws Exception;

    /**
     * 构造消息发送器
     *
     * @param properties 无前缀属性
     * @return 发送器
     * @throws Exception 异常
     */
    MessageSender createSender() throws Exception;
}
