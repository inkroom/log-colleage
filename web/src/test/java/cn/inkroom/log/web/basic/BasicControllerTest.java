package cn.inkroom.log.web.basic;

import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author 墨盒
 * @date 19-2-9
 */
@WebAppConfiguration
@ContextConfiguration({"classpath:spring/application.xml", "classpath:spring/applicationMVC.xml"})
public class BasicControllerTest extends BasicDaoTest {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    protected MockMvc mvc;
    @Autowired
    protected WebApplicationContext context;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
}
