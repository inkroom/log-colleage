package cn.inkroom.log.test.dao;

import base.BaseTest;
import cn.inkroom.log.model.LogBackup;
import cn.inkroom.log.server.dao.h2.BackupDao;
import cn.inkroom.log.server.server.log.LogService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @author 墨盒
 * @date 19-2-8
 */


public class LogBackupTest extends BaseTest {
    @Autowired
    private BackupDao backupDao;

    @Test
    public void test() throws Exception {

        LogBackup file = new LogBackup();
        file.setPath("/path.log");
        file.setCreated(new Date());
        file.setEnd(new Date());
        file.setStart(new Date());
        file.setSize(100L);
        file.setLength(200L);
        Assert.assertEquals("插入日志文件记录失败", 1, backupDao.insertFile(file));

        logger.debug(file.toString());
    }
}
