/**
 * Copyright © 2019 inkbox (enpassPixiv@protonmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.inkroom.log.test.dao;

import cn.inkroom.log.web.basic.BasicDaoTest;
import cn.inkroom.log.web.basic.BasicTest;
import cn.inkroom.log.model.Server;
import cn.inkroom.log.web.dao.ServerDao;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * @author 墨盒
 * @date 19-2-9
 */

public class ServerDaoTest extends BasicDaoTest {

    @Autowired
    private ServerDao dao;

    @Test
    public void testInsertServer() throws Exception {

        Assert.assertEquals(1, insertServer("127.0.0.1"));
    }

    private int insertServer(String ip) throws Exception {
        Server server = new Server();
        server.setIp(ip);
        server.setFilePort(new Random().nextInt(3222));
        server.setLast(new Date());
        server.setRun(new Random().nextLong());
        server.setStatus(true);
        return dao.insertServer(server);
    }

    @Test
    public void testSelectServerByIp() throws Exception {
        String ip = "192.18.34.1";
        insertServer(ip);
        Server server = dao.selectServerByIp(ip);
        Assert.assertNotNull(server);
    }

    @Test
    @Rollback
    public void testSelectServer() throws Exception {
        // 单个测试没问题，，，但是整个test就会报错，可能是数据回滚有问题
        jdbcTemplate.update("delete from SERVER");

        String ip = "192.15.34.1";
        insertServer(ip);
        ip = "192.22.34.43";
        insertServer(ip);

        List<Server> list = dao.selectServer();

        Assert.assertEquals(2, list.size());

        Assert.assertEquals("集合错误的结果", ip, list.get(list.size() - 1).getIp());

    }

    @Test
    public void testUpdateServer() throws Exception {
        String ip = "192.85.34.1";
        insertServer(ip);

        //修改存在的数据

        Server server = new Server();
        server.setIp(ip);

        server.setFilePort(1333);
        server.setStatus(false);
        server.setRun(1232L);
        server.setLast(new Date());

        Assert.assertEquals(1, dao.updateServer(server));


        server.setIp(UUID.randomUUID().toString().substring(0, 12));
        //修改不存在的数据
        Assert.assertEquals(0, dao.updateServer(server));

    }


    @Test
    public void testUpdateAllServerStop() throws Exception {

//        jdbcTemplate.update("delete from SERVER");

        String ip = "19.185.34.1";
        insertServer(ip);
        ip = "192.18.32.1";
        insertServer(ip);

        Assert.assertEquals(2, dao.updateAllServerStop());
    }

    /**
     * 一次性把所有方法测试完
     *
     * @throws Exception
     */
    @Test
    @Ignore
    public void test() throws Exception {
        //添加两条数据
        Server server = new Server();
        server.setIp("127.0.0.1");
        server.setFilePort(843);
        server.setLast(new Date());
        server.setRun(1000000L);
        server.setStatus(true);


        Assert.assertEquals(1, dao.insertServer(server));

        server = dao.selectServerByIp("127.0.0.1");
        Assert.assertNotNull(server);
        logger.debug("{}", server);


        server.setFilePort(999);
        Assert.assertEquals("修改失败", 1, dao.updateServer(server));

        Assert.assertEquals("清空失败", 1, dao.updateAllServerStop());

        List<Server> list = dao.selectServer();

        Assert.assertEquals("查询错误", 1, list.size());

        Assert.assertFalse("清空错误", list.get(0).isStatus());

    }

}
