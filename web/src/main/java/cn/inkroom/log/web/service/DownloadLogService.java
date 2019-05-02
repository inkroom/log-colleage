package cn.inkroom.log.web.service;

import cn.inkroom.log.web.bean.DownloadLog;
import cn.inkroom.log.web.bean.LoginLog;
import cn.inkroom.log.web.dao.DownloadLogDao;
import cn.inkroom.log.web.dao.LoginLogDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 墨盒
 * @date 19-4-30
 */
@Service
public class DownloadLogService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DownloadLogDao dao;

    public List<LoginLog> loginLogs() {
        try {
            return dao.selectLog();
        } catch (Exception e) {
            logger.error("获取登录日志", e);
            return null;
        }
    }

    public int insertLog(DownloadLog log) {
        try {
            return dao.insertLog(log);
        } catch (Exception e) {
            logger.error("添加下载记录失败", e);
            return -1;
        }
    }

}
