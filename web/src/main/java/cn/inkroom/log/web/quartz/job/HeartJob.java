package cn.inkroom.log.web.quartz.job;

import cn.inkroom.log.web.dao.ServerDao;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 心跳包job<br>
 * 定时清除没有收到心跳包的server
 *
 * @author 墨盒
 * @date 19-2-9
 */
public class HeartJob extends QuartzJobBean {

    @Autowired
    private ServerDao dao;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            logger.debug("清除所有server状态");
            dao.updateAllServerStop();
        } catch (Exception e) {
            throw new JobExecutionException(e);
        }
    }
}
