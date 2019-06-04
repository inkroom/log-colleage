package controller;

import cn.inkroom.log.web.basic.BasicControllerTest;
import cn.inkroom.log.web.bean.LoginLog;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author 墨盒
 * @date 19-2-9
 */
public class AuthControllerTest extends BasicControllerTest {


    @Test
    @Rollback
    @Transactional
    public void testLogin() throws Exception {
        jdbcTemplate.execute("delete  from LOGIN_LOG");
        String url = "/auth/login";
        MvcResult result = mvc.perform(get(url)
<<<<<<< HEAD
                .param("username", "admin")
                .param("password", "admin")
=======
                .param("username", username)
                .param("password", password)
>>>>>>> 4d1029b3b78a8785d92e47153f90fbe2cf99e0f5
                .header("User-Agent", "Junit test")
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
    }

}
