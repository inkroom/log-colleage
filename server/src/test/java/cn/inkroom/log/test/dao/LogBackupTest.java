/**
 * Copyright © 2019 inkbox (enpassPixiv@protonmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.inkroom.log.test.dao;

import base.BaseTest;
import cn.inkroom.log.model.LogBackup;
import cn.inkroom.log.server.dao.h2.BackupDao;
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
        file.setPath("/home/inkbox/backup/path.log");
        file.setCreated(new Date());
        file.setEnd(new Date());
        file.setStart(new Date());
        file.setSize(100L);
        file.setLength(200L);
        Assert.assertEquals("插入日志文件记录失败", 1, backupDao.insertFile(file));

        logger.debug(file.toString());
    }
}
