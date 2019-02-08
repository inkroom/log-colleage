package cn.inkroom.log.server.config;

import cn.inkroom.log.quartz.SchedulerManager;
import cn.inkroom.log.server.handler.PropertiesHandler;
import cn.inkroom.log.server.quartz.job.backup.BackupJob;
import cn.inkroom.log.server.quartz.job.heartBeat.HeartBeatJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public QuartzConfig(SchedulerManager manager) {
        String cron = PropertiesHandler.getProperty("quartz.heartbeat.cron");
        logger.info("构建心跳包定时任务,cron={}", cron);
        manager.addJob(HeartBeatJob.class, "heartbeat", "heartbeat_group", cron);

        cron = PropertiesHandler.getProperty("quartz.backup.cron");
        logger.info("构建定时备份任务，cron={}", cron);
        manager.addJob(BackupJob.class, "backup", "backup_group", cron);
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
