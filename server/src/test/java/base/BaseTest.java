package base;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;

/**
 * @author 墨盒
 * @date 19-2-8
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath*:application.xml"})
public abstract class BaseTest {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected JdbcTemplate jdbcTemplate;
    @Autowired
    private DataSource dataSource;

//    @BeforeClass
//    public void beforeClass() throws Exception {
//        if (dataSource != null) {
//            jdbcTemplate = new JdbcTemplate(dataSource);
//            jdbcTemplate.execute(getInitSql());
//        }
//
//    }

    @Before
    public void setUp() throws Exception {
        if (jdbcTemplate == null && dataSource != null) {
            jdbcTemplate = new JdbcTemplate(dataSource);
            jdbcTemplate.execute(getInitSql());
        }
    }

    private String getInitSql() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/log_server.sql")));
        String temp = null;
        StringBuilder builder = new StringBuilder();
        while ((temp = reader.readLine()) != null) {
            builder.append(temp);
        }
        logger.debug("获取的sql内容={}", builder);
        return builder.toString();
    }
}
