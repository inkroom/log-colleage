package cn.inkroom.log.server.dao.h2;

import cn.inkroom.log.model.LogBackup;

/**
 * 用于存储日志文件路径等数据<br>
 * 使用h2数据库
 *
 * @author 墨盒
 * @date 19-2-8
 */
public interface BackupDao {

    int insertFile(LogBackup file) throws Exception;

    /**
     * 筛选出最新一条记录
     *
     * @return 记录
     * @throws Exception 异常
     */
    LogBackup selectLast() throws Exception;
}
