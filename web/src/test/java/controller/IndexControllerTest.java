package controller;

import cn.inkroom.log.web.basic.BasicControllerTest;
import cn.inkroom.log.web.basic.BasicTest;
import cn.inkroom.log.web.bean.MessageDto;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author 墨盒
 * @date 19-2-9
 */
public class IndexControllerTest extends BasicControllerTest {

    @Test
    public void testIndex() throws Exception {
        String url = "/index";
        mvc.perform(get(url)).andExpect(status().isOk()).andExpect(view().name("index"));

    }

    @Test
    public void testList() throws Exception {
        String url = "/list";
        MvcResult result = mvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(content().string(new MessageDto<>(0, new ArrayList<>()).toString()))
                .andExpect(jsonPath("$.code").value(0))
                .andReturn();

        logger.info("响应的结果={}", result.getResponse().getContentAsString());

    }

}
