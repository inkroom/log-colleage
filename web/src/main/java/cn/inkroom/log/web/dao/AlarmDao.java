package cn.inkroom.log.web.dao;

import cn.inkroom.log.web.bean.Alarm;
import cn.inkroom.log.web.bean.LoginLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 墨盒
 * @date 19-5-6
 */
public interface AlarmDao {
    /**
     * 记录登录日志
     *
     * @return
     * @throws Exception
     */
    int insertLog(@Param("a") Alarm alarm) throws Exception;

    /**
     * 获取登录日志
     *
     * @return
     * @throws Exception
     */
    List<Alarm> selectAlarm() throws Exception;
}
