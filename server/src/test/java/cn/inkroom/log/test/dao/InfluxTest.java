package cn.inkroom.log.test.dao;

import base.BaseTest;
import cn.inkroom.log.model.LogMsg;
import cn.inkroom.log.server.dao.LogDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 测试influx连接是
 *
 * @author 墨盒
 * @date 19-1-8
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({"classpath*:application.xml"})
public class InfluxTest extends BaseTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private LogDao dao;

    @Test
    public void testInsert() throws Exception {


        LogMsg msg = new LogMsg();
        msg.setIp("127.0.0.1");
        msg.setTag("junit");
        msg.setTime(new Date());
        msg.setMsg("debug:自定义时间戳的数据");


        dao.insert(msg);


    }

    @Test
    public void testQuery() throws Exception {


        Date now = new Date();
        now.setTime(now.getTime() - 3600 * 1000);

        List<LogMsg> msg = dao.selectByTime(now.getTime() - 3600 * 1000, new Date().getTime());
        logger.debug("{}", msg);
        if (!msg.isEmpty()) {
            logger.debug("时间，注意时区={}", msg.get(0).getTime());
        }


    }


}
