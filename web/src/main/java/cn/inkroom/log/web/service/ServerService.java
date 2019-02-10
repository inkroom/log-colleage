package cn.inkroom.log.web.service;

import cn.inkroom.log.model.Server;
import cn.inkroom.log.mq.MessageCenter;
import cn.inkroom.log.mq.MessageListener;
import cn.inkroom.log.web.dao.ServerDao;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 各个server状态
 *
 * @author 墨盒
 * @date 19-2-9
 */
@Service
public class ServerService implements MessageListener {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private String heartChannel;
    @Autowired
    private ServerDao dao;

    public ServerService(@Autowired MessageCenter center, @Value("${mq.channel.topic.heartbeat}") String heartChannel) {
        logger.info("注册心跳包消息监听，频道={},类型=topic", heartChannel);
        center.addListener(this, heartChannel, true);
        this.heartChannel = heartChannel;
    }

    public List<Server> getServerList() throws RuntimeException {
        try {
            return dao.selectServer();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据ip地址删除
     *
     * @param ip ip
     * @return 受影响行数
     * @throws Exception 异常
     */
    public int deleteServer(String ip) throws RuntimeException {
        try {
            return dao.deleteServerByIp(ip);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean onMessage(String message, String channel) {
        if (channel.equals(heartChannel)) {
            //转换成server
            Server server = JSONObject.parseObject(message, Server.class);
            if (server != null) {
                //存储到数据库中
                try {
                    Server saved = dao.selectServerByIp(server.getIp());
                    int count;
                    if (saved == null) {
                        count = dao.insertServer(server);
                    } else {
                        count = dao.updateServer(server);
                    }
                    if (count != 1) {
                        logger.warn("server状态更新失败，server={}", server);
                    }
                } catch (Exception e) {
                    logger.warn("server状态更新失败,", e);
                }
            } else {
                logger.warn("错误的消息不能转换成{},channel={},message={}", Server.class, channel, message);
            }
            return true;
        }
        return false;
    }
}
