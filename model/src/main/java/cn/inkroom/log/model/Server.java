package cn.inkroom.log.model;

import com.alibaba.fastjson.JSONObject;

import java.util.Date;

/**
 *
 * server的实例
 * @author 墨盒
 * @date 19-2-9
 */
public class Server {

    private String ip;
    private long run;
    private boolean status;
    private int filePort;
    private Date last;

    private long start;

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public long getRun() {
        return run;
    }

    public void setRun(long run) {
        this.run = run;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getFilePort() {
        return filePort;
    }

    public void setFilePort(int filePort) {
        this.filePort = filePort;
    }

    public Date getLast() {
        return last;
    }

    public void setLast(Date last) {
        this.last = last;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
