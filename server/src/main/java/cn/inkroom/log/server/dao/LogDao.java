package cn.inkroom.log.server.dao;

import cn.inkroom.log.model.LogMsg;

import java.util.List;

/**
 * @author 墨盒
 * @date 19-1-8
 */
public interface LogDao {

    /**
     * 入库
     *
     * @param msg 日志消息
     * @return 受影响行数
     * @throws Exception
     */
    int insert(LogMsg msg) throws Exception;

    /**
     * 获取指定时间范围内的数据
     *
     * @param start 开始时间戳
     * @param end   结束时间戳
     * @return 日志消息集合
     * @throws Exception
     */
    List<LogMsg> selectByTime(long start, long end) throws Exception;
}
