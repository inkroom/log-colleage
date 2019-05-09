package cn.inkroom.log.web.service;

import cn.inkroom.log.web.bean.Statistics;
import cn.inkroom.log.web.dao.StatisticsDao;
import com.sun.xml.internal.bind.v2.TODO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 墨盒
 * @date 19-5-9
 */
@Service
public class StatisticsService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private StatisticsDao dao;

    public List<String> getTags() {
        return dao.selectTags();
    }

    public List<String> getIP() {
        return dao.selectIP();
    }

    /**
     * 更新记录
     *
     * @param tag   tag
     * @param ip    ip
     * @param count 要变化的数据，如+1,-1
     * @param level 级别
     * @return
     */
    public boolean updateCount(String tag, String ip, int level,int count ) {

        int res = dao.update(tag, ip, level, count);
        if (res == 0) {//没有数据

            Statistics statistics = new Statistics();
            statistics.setCount(count);
            statistics.setLevel(level);
            statistics.setIp(ip);
            statistics.setTag(tag);

            return dao.insert(statistics) == 1;


        }
        return res == 1;
    }

    public List<Statistics> list(String tag, String ip, int level, int page, int size) {

        //TODO 19-5-9 预留分页功能
        return dao.select(tag, ip, level);
    }
}
