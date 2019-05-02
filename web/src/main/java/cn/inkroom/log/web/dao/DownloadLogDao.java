package cn.inkroom.log.web.dao;

import cn.inkroom.log.web.bean.DownloadLog;
import cn.inkroom.log.web.bean.LoginLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 下载日志
 *
 * @author 墨盒
 * @date 19-4-30
 */
public interface DownloadLogDao {
    /**
     * 记录下载日志
     *
     * @return
     * @throws Exception
     */
    int insertLog(@Param("d") DownloadLog log) throws Exception;

    /**
     * 获取日志
     *
     * @return
     * @throws Exception
     */
    List<LoginLog> selectLog() throws Exception;
}
