package cn.inkroom.log.web.bean;

import java.util.Date;

/**
 * 登录记录
 * @author 墨盒
 * @date 19-4-30
 */
public class LoginLog {


    private String ip;
    private Date createdTime;
    private String ua;
    private String account;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getUa() {
        return ua;
    }

    public void setUa(String ua) {
        this.ua = ua;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "LoginLog{" +
                "ip='" + ip + '\'' +
                ", createdTime=" + createdTime +
                ", ua='" + ua + '\'' +
                ", account='" + account + '\'' +
                '}';
    }
}
