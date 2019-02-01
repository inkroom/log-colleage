package cn.inkroom.log.server.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * influxdb数据库配置信息
 * @author 墨盒
 * @date 18-11-20
 */
public class InfluxdbConfig {

    private Logger logger = LoggerFactory.getLogger(getClass());


    private String ip;
    private int port;
    private String username;
    private String password;

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
