package cn.inkroom.log.web.quartz.job;

import cn.inkroom.log.model.Server;
import cn.inkroom.log.web.dao.ServerDao;
import cn.inkroom.log.web.service.DownTimeService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

/**
 * 用于记录宕机job<br>
 * 定时清除没有收到心跳包的server
 *
 * @author 墨盒
 * @date 19-2-9
 */
public class DownTimeJob extends QuartzJobBean {

    @Autowired
    private ServerDao dao;
    @Autowired
    private DownTimeService service;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            //获取宕机的机器
            List<Server> servers = dao.selectServer();
            for (int i = 0; i < servers.size(); i++) {
                if (!servers.get(i).isStatus()) {//已宕机
                    logger.debug("记录宕机，{}", servers.get(i));
                    service.addDownTime(servers.get(i).getIp(), null, servers.get(i).getLast());
                }
            }
        } catch (Exception e) {
            throw new JobExecutionException(e);
        }
    }
}
