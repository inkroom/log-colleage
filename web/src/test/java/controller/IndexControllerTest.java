package controller;

import cn.inkroom.log.web.basic.BasicControllerTest;
import cn.inkroom.log.web.basic.BasicTest;
import cn.inkroom.log.web.bean.MessageDto;
import org.junit.Before;
<<<<<<< HEAD
=======
import org.junit.Ignore;
>>>>>>> 4d1029b3b78a8785d92e47153f90fbe2cf99e0f5
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
<<<<<<< HEAD
=======

>>>>>>> 4d1029b3b78a8785d92e47153f90fbe2cf99e0f5
public class IndexControllerTest extends BasicControllerTest {

    @Test
    public void testIndex() throws Exception {
        String url = "/index";
<<<<<<< HEAD
        mvc.perform(get(url)).andExpect(status().isOk()).andExpect(view().name("index"));
=======
        mvc.perform(get(url).session(session)).andExpect(status().isOk()).andExpect(view().name("index"));
>>>>>>> 4d1029b3b78a8785d92e47153f90fbe2cf99e0f5

    }

    @Test
    public void testList() throws Exception {
        String url = "/list";
<<<<<<< HEAD
        MvcResult result = mvc.perform(get(url))
=======
        MvcResult result = mvc.perform(get(url).session(session))
>>>>>>> 4d1029b3b78a8785d92e47153f90fbe2cf99e0f5
                .andExpect(status().isOk())
//                .andExpect(content().string(new MessageDto<>(0, new ArrayList<>()).toString()))
                .andExpect(jsonPath("$.code").value(0))
                .andReturn();

        logger.info("响应的结果={}", result.getResponse().getContentAsString());

    }

}
