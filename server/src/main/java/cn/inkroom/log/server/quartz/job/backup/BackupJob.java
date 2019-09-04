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
package cn.inkroom.log.server.quartz.job.backup;

import cn.inkroom.log.model.LogBackup;
import cn.inkroom.log.server.dao.h2.BackupDao;
import cn.inkroom.log.server.service.BackupService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.io.File;
import java.util.Date;

/**
 * 日志文件备份定时任务
 *
 * @author 墨盒
 * @date 19-2-8
 */
public class BackupJob extends QuartzJobBean {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private BackupDao dao;
    @Autowired
    private BackupService service;
    @Value("${quartz.backup.dir}")
    private String dir;

    @Override
    public void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        try {
            LogBackup backup = dao.selectLast();
            if (backup == null) {
                backup = new LogBackup();
                backup.setStart(new Date(0L));
            } else {
                backup.setStart(backup.getEnd());
            }
            backup.setEnd(new Date());
            logger.debug("备份开始时间={},结束时间={}",backup.getStart(),backup.getEnd());
            backup.setPath(dir + File.separator + System.currentTimeMillis() + ".log");

            int size = service.backup(backup.getStart().getTime(), backup.getEnd().getTime(), backup.getPath());
            if (size > 0) {//没有日志被存储，不入库
                backup.setCreated(new Date());
                backup.setSize(size);
                backup.setLength(new File(backup.getPath()).length());
                logger.debug("要备份的记录={}",backup);
                if (dao.insertFile(backup) != 1) {
                    logger.warn("存储日志文件路径失败，文件信息={}", backup);
                }
                //存入备份记录
            }

        } catch (Exception e) {
            throw new JobExecutionException(e);
        }

//        service.ba

    }
}
