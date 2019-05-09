package controller;

import cn.inkroom.log.web.basic.BasicControllerTest;
import org.junit.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author 墨盒
 * @date 19-2-9
 */
public class StatisticsControllerTest extends BasicControllerTest {

    @Test
    public void testIndex() throws Exception {
        String url = "/index";
        mvc.perform(get(url)).andExpect(status().isOk()).andExpect(view().name("index"));

    }

    @Test
    public void testList() throws Exception {

        MockHttpSession session = new MockHttpSession();
        String url = "/auth/login";
        mvc.perform(get(url)
                .param("username", "admin")
                .param("password", "admin")
                .header("User-Agent", "Junit test")
                .session(session)
        )
                .andExpect(status().isOk())
//                .andExpect(content().string(new MessageDto<>(0, new ArrayList<>()).toString()))
                .andExpect(jsonPath("$.code").value(0));


        url = "/statistics";
        MvcResult result = mvc.perform(post(url).session(session))
                .andExpect(status().isOk())
//                .andExpect(content().string(new MessageDto<>(0, new ArrayList<>()).toString()))
                .andExpect(jsonPath("$.code").value(0))
                .andReturn();

        logger.info("响应的结果={}", result.getResponse().getContentAsString());

    }

}
