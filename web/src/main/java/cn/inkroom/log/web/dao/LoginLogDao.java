package cn.inkroom.log.web.dao;

import cn.inkroom.log.web.bean.LoginLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 登录日志
 *
 * @author 墨盒
 * @date 19-4-30
 */
public interface LoginLogDao {
    /**
     * 记录登录日志
     *
     * @param ip      ip地址
     * @param ua      使用的浏览器
     * @param account 登录的账号
     * @return
     * @throws Exception
     */
    int insertLog(@Param("l") LoginLog log) throws Exception;

    /**
     * 获取登录日志
     *
     * @return
     * @throws Exception
     */
    List<LoginLog> selectLog() throws Exception;
}
