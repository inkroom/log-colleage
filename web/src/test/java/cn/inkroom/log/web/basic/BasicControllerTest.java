package cn.inkroom.log.web.basic;

import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @author 墨盒
 * @date 19-2-9
 */
@WebAppConfiguration
@ContextConfiguration({"classpath:spring/application.xml", "classpath:spring/applicationMVC.xml"})
@Transactional
@Rollback
public class BasicControllerTest extends BasicDaoTest {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    protected MockMvc mvc;
    @Autowired
    protected WebApplicationContext context;


    protected MockHttpSession session;

    @Value("${account.username}")
    protected String username;
    @Value("${account.password}")
    protected String password;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();

        session = new MockHttpSession();
        mvc.perform(post("/auth/login").session(session)
                .header("User-Agent", "Junit test")
                .param("username", username)
                .param("password", password)).andExpect(status().isOk()
        )
                .andExpect(jsonPath("$.code").value(0));
    }
}
