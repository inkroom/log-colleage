package cn.inkroom.log.server.quartz;

import org.quartz.spi.TriggerFiredBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/11/10
 * @Time 10:53
 * @Descorption
 */
public class AutoSpringBeanJobFactory extends SpringBeanJobFactory {

    private Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private AutowireCapableBeanFactory beanFactory;

    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        Object jobInstance = super.createJobInstance(bundle);
        beanFactory.autowireBean(jobInstance);
        log.info("这里被处罚");
        return jobInstance;
    }
}