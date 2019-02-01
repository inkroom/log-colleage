package cn.inkroom.log.model;


import com.alibaba.fastjson.JSON;

import java.util.Date;

/**
 * 日志消息
 *
 * @author 墨盒
 * @date 18-11-20
 */
public class LogMsg {

    private String ip;
    private Date time;
    private String tag;
    private String msg;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
//        return "LogMsg{" +
//                "ip='" + ip + '\'' +
//                ", time=" + time +
//                ", tag='" + tag + '\'' +
//                ", msg='" + msg + '\'' +
//                '}';
    }

    public static LogMsg getInstanceFromJson(String value) throws Exception {
        return JSON.parseObject(value, LogMsg.class);
    }

    public LogMsg() {
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

}
