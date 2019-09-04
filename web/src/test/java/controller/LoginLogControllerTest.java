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
package controller;

import cn.inkroom.log.web.basic.BasicControllerTest;
import cn.inkroom.log.web.bean.LoginLog;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author 墨盒
 * @date 19-2-9
 */
public class LoginLogControllerTest extends BasicControllerTest {


    @Test
    @Rollback
    @Transactional
    public void testList() throws Exception {
        jdbcTemplate.execute("delete  from LOGIN_LOG");

        MockHttpSession session = new MockHttpSession();
       String url = "/auth/login";
        MvcResult result = mvc.perform(get(url)
                .param("username", "admin")
                .param("password", "admin")
                .header("User-Agent", "Junit test")
                .session(session)
        )
                .andExpect(status().isOk())
//                .andExpect(content().string(new MessageDto<>(0, new ArrayList<>()).toString()))
                .andExpect(jsonPath("$.code").value(0))
                .andReturn();

        logger.info("响应的结果={}", result.getResponse().getContentAsString());

        Integer i = jdbcTemplate.queryForObject("select count(1) from LOGIN_LOG", Integer.class);
        logger.debug("行数={}", i);
        LoginLog log = jdbcTemplate.queryForObject("select * from LOGIN_LOG", new RowMapper<LoginLog>() {
            @Override
            public LoginLog mapRow(ResultSet resultSet, int i) throws SQLException {

                LoginLog log = new LoginLog();
                log.setAccount(resultSet.getString("ACCOUNT"));
                log.setIp(resultSet.getString("IP"));
                log.setUa(resultSet.getString("UA"));
                log.setCreatedTime(resultSet.getDate("CREATEDTIME"));
                return log;
            }
        });

        Assert.assertNotNull("插入登录记录失败", log);

        logger.info("日志={}", log);




        url = "/login_log";
        result = mvc.perform(post(url)
                .session(session)
        )
                .andExpect(status().isOk())
//                .andExpect(content().string(new MessageDto<>(0, new ArrayList<>()).toString()))
                .andExpect(jsonPath("$.code").value(0))
                .andReturn();

        logger.info("响应的结果={}", result.getResponse().getContentAsString());

    }

}
