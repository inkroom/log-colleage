import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

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
                "可以支持日常使用的几乎所有类型的浏览器，涵盖开", "场一直与众多优秀的第三方服务供应", "助力各行各业客户成功上云的案例", "腾讯云全球基础设施", "开发者社区", "提供安全专家服务和合规性资质",
                "但是用ref绑定之后，我们就不需要在获取dom节点了，直接在上面的input上绑定input1，然后$refs里面调用就行。", "注册 即可领取40+款产品的免费体验套餐，更有开发者专属 在线实验平台", "立即体验", "tencent 腾讯开放平台 QQ物联 DNSPod 微信公众平台 腾讯优图 腾讯蓝鲸 企业QQ 腾讯微云 腾讯文档 友情链接",
                "Apache 2.0\t\tcommons-net » commons-net", "静态爬虫就无法爬取动态生成的页", "支持多种传送协议:in-VM,TCP,SSL,NIO,UDP,JGroups,JXTA", "对Spring的支持,ActiveMQ可以很容易内嵌到使用Spring的系统里面去,而且也支持Spring2.0的特性",
                "个项目之间集成 \n" +
                        "(1) 跨平台 \n" +
                        "(2) 多语言 \n" +
                        "(3) 多项目", "解析动态页面一般要触发页面事件才能执行脚本 \n" +
                "(1) 前后端隔离，", "系统间模块的耦合度系统间模块的耦合度系统间模块的耦合度系统间模块的耦合度系统间模块的耦合度系统间模块的耦合度系统间模块的耦合度系统间模块的耦合度系统间模块的耦合度系统间模块的耦合度系统间模块的耦合度，解耦 ", "深入浅出Redis-redis哨兵集群", "博主，可否做一个实际带java调用你这个ActiveMQ消息队列例子。谢谢……", "楼主，求你Java部分一整块Demo。邮箱：1146135461@qq.com"};

        for (int i = 0; i < 10; i++) {
            logger.info(str[new Random().nextInt(str.length)]);
        }

//        logger.debug("测试tag");

    }
}
