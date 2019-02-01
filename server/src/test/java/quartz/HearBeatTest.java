package quartz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author 墨盒
 * @date 19-2-1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:application.xml"})
public class HearBeatTest {

    @Test
    public void testHeartBeat() throws Exception {

        Thread.sleep(1000 * 60 * 4);
    }
}
