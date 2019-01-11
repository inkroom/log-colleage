package dao;

import cn.inkroom.log.model.LogMsg;
import cn.inkroom.log.server.dao.LogDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * 测试influx连接是
 *
 * @author 墨盒
 * @Date 19-1-8
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:application.xml"})
public class InfluxTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private LogDao dao;

    @Test
    public void testInsert() throws Exception {


        LogMsg msg = new LogMsg();
        msg.setIp("127.0.0.1");
        msg.setTag("junit");
        msg.setTime(new Date(123));
        msg.setMsg("debug:自定义时间戳的数据");


        dao.insert(msg);


    }

    @Test
    public void testQuery() throws Exception {


        dao.selectByTime(0, new Date().getTime());


    }


}
