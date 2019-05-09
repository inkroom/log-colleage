package service;

import cn.inkroom.log.model.LogMsg;
import cn.inkroom.log.web.basic.BasicDaoTest;
import cn.inkroom.log.web.basic.BasicTest;
import cn.inkroom.log.web.service.log.impl.StatisticsLogServer;
import com.alibaba.fastjson.JSON;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 墨盒
 * @date 19-5-9
 */
public class StatisticsLogServerTest extends BasicDaoTest {
    @Autowired
    private StatisticsLogServer server;

    @Test
    @Rollback
    @Transactional
    public void testStatistics() throws Exception {

        jdbcTemplate.update("delete from STATISTICS");

        LogMsg msg = new LogMsg();
        msg.setTag("322323232");
        msg.setIp("3232.55.22.2.2");
        msg.setLevel(21);
        msg
                .setMsg("2132332消息");
        server.onMessage(JSON.toJSONString(msg), "");
        server.onMessage(JSON.toJSONString(msg), "");


        Integer count = jdbcTemplate.queryForObject("select count(1) from statistics where tag=? and ip=? and level=?",
                new Object[]{msg.getTag(), msg.getIp(), msg.getLevel()},
                Integer.class);
        Assert.assertNotNull(count);
        Assert.assertEquals(1, count.intValue());
    }

}
