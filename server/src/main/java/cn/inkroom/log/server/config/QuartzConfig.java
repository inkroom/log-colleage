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
package cn.inkroom.log.server.config;

import cn.inkroom.log.quartz.SchedulerManager;
import cn.inkroom.log.server.handler.PropertiesHandler;
import cn.inkroom.log.server.quartz.job.backup.BackupJob;
import cn.inkroom.log.server.quartz.job.heartBeat.HeartBeatJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 定时任务配置
 *
 * @author 墨盒
 * @date 19-2-1
 */
@Configuration
public class QuartzConfig {

    private Logger logger = LoggerFactory.getLogger(getClass());

    //    @Autowired
//    private SchedulerManager manager;
    @Autowired
    public QuartzConfig(SchedulerManager manager, @Value("${quartz.backup.enable}") boolean backupEnable) {
        String cron = PropertiesHandler.getProperty("quartz.heartbeat.cron");
        logger.info("构建心跳包定时任务,cron={}", cron);
        manager.addJob(HeartBeatJob.class, "heartbeat", "heartbeat_group", cron);

        if (backupEnable) {
            cron = PropertiesHandler.getProperty("quartz.backup.cron");
            logger.info("构建定时备份任务，cron={}", cron);
            manager.addJob(BackupJob.class, "backup", "backup_group", cron);
        }

    }


//    public QuartzConfig() {
//        logger.debug("定时任务配置");
//    }

//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent contextStartedEvent) {
////        String cron = PropertiesHandler.getProperty("quartz.heartbeat.cron");
////        logger.info("构建心跳包定时任务,cron={}", cron);
////        manager.addJob(HeartBeatJob.class, "heartbeat", "heartbeat_group", cron);
//    }
}
