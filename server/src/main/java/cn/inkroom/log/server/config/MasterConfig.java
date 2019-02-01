package cn.inkroom.log.server.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 墨盒
 * @date 18-11-20
 */
public class MasterConfig {

    private Logger logger = LoggerFactory.getLogger(getClass());


    private String ip;
    private int port;


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
