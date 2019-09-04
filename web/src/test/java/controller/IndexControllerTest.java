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
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;

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
        mvc.perform(get(url).session(session)).andExpect(status().isOk()).andExpect(view().name("index"));

    }

    @Test
    public void testList() throws Exception {
        String url = "/list";
        MvcResult result = mvc.perform(get(url).session(session))
                .andExpect(status().isOk())
//                .andExpect(content().string(new MessageDto<>(0, new ArrayList<>()).toString()))
                .andExpect(jsonPath("$.code").value(0))
                .andReturn();

        logger.info("响应的结果={}", result.getResponse().getContentAsString());

    }

}
