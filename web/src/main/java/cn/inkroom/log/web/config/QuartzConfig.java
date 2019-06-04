package cn.inkroom.log.web.config;

import cn.inkroom.log.quartz.SchedulerManager;
<<<<<<< HEAD
import cn.inkroom.log.web.handler.PropertiesHandler;
=======
import cn.inkroom.log.web.dao.DownTimeDao;
import cn.inkroom.log.web.handler.PropertiesHandler;
import cn.inkroom.log.web.quartz.job.DownTimeJob;
>>>>>>> 4d1029b3b78a8785d92e47153f90fbe2cf99e0f5
import cn.inkroom.log.web.quartz.job.HeartJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.AbstractEnvironment;

/**
 * @author 墨盒
 * @date 19-2-9
 */
@Configuration
public class QuartzConfig {
    private Logger logger = LoggerFactory.getLogger(getClass());
<<<<<<< HEAD
    @Autowired
    public QuartzConfig( SchedulerManager manager) {
        String cron = PropertiesHandler.getProperty("quartz.heartbeat.cron");
        logger.info("清理心跳包定时任务,cron={}", cron);
        manager.addJob(HeartJob.class, "heart", "heart_group", cron);
=======

    @Autowired
    public QuartzConfig(SchedulerManager manager) {
        String cron = PropertiesHandler.getProperty("quartz.heartbeat.cron");
        logger.info("清理心跳包定时任务,cron={}", cron);
        manager.addJob(HeartJob.class, "heart", "heart_group", cron);

        logger.info("加入宕机记录定时任务,cron={}", cron);
        manager.addJob(DownTimeJob.class, "down", "down_group", cron);
>>>>>>> 4d1029b3b78a8785d92e47153f90fbe2cf99e0f5
    }
}
