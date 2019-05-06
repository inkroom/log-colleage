package cn.inkroom.log.web.service;

import cn.inkroom.log.web.bean.Alarm;
import cn.inkroom.log.web.bean.LoginLog;
import cn.inkroom.log.web.dao.AlarmDao;
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
public class AlarmService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private AlarmDao dao;

    public int insertAlarm(Alarm alarm) {
        try {
            return dao.insertLog(alarm);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return -1;
    }

    public List<Alarm> selectAlarms() {
        try {
            return dao.selectAlarm();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

}
