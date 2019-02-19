package cn.inkroom.log.server.quartz.job.backup;

import cn.inkroom.log.model.LogBackup;
import cn.inkroom.log.server.dao.h2.BackupDao;
import cn.inkroom.log.server.server.log.LogService;
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
    private LogService service;
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
            backup.setPath(dir + File.separator + System.currentTimeMillis() + ".log");

            int size = service.backup(backup.getStart().getTime(), backup.getEnd().getTime(), backup.getPath());
            if (size > 0) {//没有日志被存储，不入库
                backup.setCreated(new Date());
                backup.setSize(size);
                backup.setLength(new File(backup.getPath()).length());
                if (dao.insertFile(backup) != 1) {
                    logger.warn("存储日志文件路径失败，文件信息={}", backup);
                }
            }

        } catch (Exception e) {
            throw new JobExecutionException(e);
        }

//        service.ba

    }
}
