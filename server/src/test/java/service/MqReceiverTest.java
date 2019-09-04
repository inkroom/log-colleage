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
package service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试mq监听
 *
 * @author 墨盒
 * @date 18-12-10
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:application.xml"})
public class MqReceiverTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void testReceiver() {
        logger.debug("slf4j是怎么拼接字符串的呢-{}-","我很好奇");

//        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:application.xml");

//        try {
//            Thread.sleep(600000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
