package cn.inkroom.log.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 墨盒
 * @Date 18-11-20
 */
public class Entry {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:application.xml");
    }
}
