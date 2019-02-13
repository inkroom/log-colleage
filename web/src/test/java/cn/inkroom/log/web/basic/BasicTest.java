package cn.inkroom.log.web.basic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 墨盒
 * @date 19-2-9
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:spring/application.xml"})

@Transactional
@Rollback
public class BasicTest {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 非得放一个test方法才行，否则要报错
     */
    @Test
    public void testBasic() {}
}
