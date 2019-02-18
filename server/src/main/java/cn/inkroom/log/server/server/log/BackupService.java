package cn.inkroom.log.server.server.log;

import cn.inkroom.log.model.LogBackup;
import cn.inkroom.log.server.dao.h2.BackupDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 墨盒
 * @date 19-2-18
 */
@Service
public class BackupService {

    @Autowired
    private BackupDao dao;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public List<LogBackup> list() throws RuntimeException {
        try {
            //TODO 19-2-18 获取文件列表失败，找不到返回值，等待debug测试
            List<LogBackup> backups = dao.selectAll();
            logger.debug("dao层获取的备份文件列表={}", backups);
            return backups;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
