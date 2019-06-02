package cn.inkroom.log.web.service;

import cn.inkroom.log.web.bean.DownTime;
import cn.inkroom.log.web.bean.DownloadLog;
import cn.inkroom.log.web.bean.LoginLog;
import cn.inkroom.log.web.dao.DownTimeDao;
import cn.inkroom.log.web.dao.DownloadLogDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author 墨盒
 * @date 19-4-30
 */
@Service
public class DownTimeService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DownTimeDao dao;

    public List<DownTime> getDwonTime(String ip, String tag) {
        return dao.selectDown(ip, tag, null);
    }

    /**
     * 添加宕机记录
     *
     * @param ip       ip
     * @param tag      暂无
     * @param lastTime 上次通讯时间，做为宕机时间
     * @return
     */
    public boolean addDownTime(String ip, String tag, Date lastTime) {

        if (dao.selectDown(ip, null, lastTime).size() == 0) {
            return dao.insertDown(ip, "", lastTime) == 1;
        }

        return false;

    }

}
