import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试mq监听
 *
 * @author 墨盒
 * @Date 18-12-10
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:application.xml"})
public class MqReceverTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void testReceiver() {

//        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:application.xml");

//        try {
//            Thread.sleep(600000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
