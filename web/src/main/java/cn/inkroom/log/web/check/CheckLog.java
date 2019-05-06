package cn.inkroom.log.web.check;

import cn.inkroom.log.model.LogMsg;

/**
 * 检测日志，并作出响应的操作
 *
 * @author 墨盒
 * @date 19-5-6
 */
public interface CheckLog {
    /**
     * 检测日志消息
     *
     * @param msg 日志
     * @return null为正常日志，其余为要发送的消息
     */
    String checkLog(LogMsg msg);

}
