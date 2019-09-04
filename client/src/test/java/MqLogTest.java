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
import com.github.jsonzou.jmockdata.JMockData;
import com.github.jsonzou.jmockdata.MockConfig;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

import static org.mockito.Mockito.mock;

/**
 * @author 墨盒
 * @date 18-12-10
 */
public class MqLogTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void test() {

        String[] str = new String[]{"随便啦", "但是网上的各个文档都写了", "不可能同时只能跑一个队列吧", "g brokerURL = \"tcp://192.16",
                "我遇到的情况跟微博访问的情况类似", "当前进度", "若干", "基础网络", "支持192个业务场景的全栈解决方案", "发、海量访问等带来的问题；如果是静态页面当然也可以用，如果是静态页面当然也可以用、游戏安全、游戏加速、全球互联互通等，让",
                "json 文件，造成页面卡卡的。后端说前端直接分页就不卡了，我觉得是在扯淡，这就是要对 71.6M的 json 进行分页，之前都是我传条数和页数的，这次不一样，emmm...... 我接收数据都需要二十多秒。求优化方案。搞不好，涵盖开", "场一直与众多优秀的第三方服务供应", "助力各行各业客户成功上云的案例", "腾讯云全球基础设施", "开发者社区", "提供安全专家服务和合规性资质",
                "但是用ref绑定之后，我们就不需要在获取dom节点了，直接在上面的input上绑定input1，然后$refs里面调用就行。", "注册 即可领取40+款产品的免费体验套餐，更有开发者专属 在线实验平台", "立即体验", "tencent 腾讯开放平台 QQ物联 DNSPod 微信公众平台 腾讯优图 腾讯蓝鲸 企业QQ 腾讯微云 腾讯文档 友情链接",
                "Apache 2.0\t\tcommons-net » commons-net", "静态爬虫就无法爬取动态生成的页", "支持多种传送协议:in-VM,TCP,SSL,NIO,UDP,JGroups,JXTA", "对Spring的支持,ActiveMQ可以很容易内嵌到使用Spring的系统里面去,而且也支持Spring2.0的特性",
                "个项目之间集成 \n" +
                        "(1) 跨平台 \n" +
                        "(2) 多语言 \n" +
                        "(3) 多项目", "解析动态页面一般要触发页面事件才能执行脚本 \n" +
                "(1) 前后端隔离，", "系统间模块的耦合度系统间模块的耦合度系统间模块的耦合度系统间模块的耦合度系统间模块的耦合度系统间模块的耦合度系统间模块的耦合度系统间模块的耦合度系统间模块的耦合度系统间模块的耦合度系统间模块的耦合度，解耦 ", "深入浅出Redis-redis哨兵集群", "博主，可否做一个实际带java调用你这个ActiveMQ消息队列例子。谢谢……", "楼主，求你Java部分一整块Demo。邮箱：1146135461@qq.com"};
        MockConfig mockConfig = new MockConfig()
                // 随机段落字符串
                .stringRegex("\\u4e00-\\u9fa5_a-zA-Z0-9]{3}dom节的页, like：[a-z]{2}-[0-9]{2}-[abc123]{2}-\\w{2}-\\d{2}@\\s{1}-\\S{1}\\.?-.");

        for (int i = 0; i < 10; i++) {

            logger.info(JMockData.mock(String.class));
        }

//        logger.debug("测试tag");

    }
}
