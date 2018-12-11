package cn.inkroom.log.server.bean;

import cn.inkroom.log.util.PropertiesField;

/**
 * @author 墨盒
 * @Date 18-12-11
 */
public class Socket {

    @PropertiesField
    private String host;
    @PropertiesField
    private int port;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
