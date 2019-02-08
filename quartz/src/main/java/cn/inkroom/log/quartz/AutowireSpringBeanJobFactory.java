package cn.inkroom.log.quartz;

import org.quartz.spi.TriggerFiredBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import org.springframework.stereotype.Component;

/**
 * @author 墨盒
 * @version 1.0
 * @date 2017/11/10
 * @Time 10:53
 * @Descorption
 */
@Component
public class AutowireSpringBeanJobFactory extends SpringBeanJobFactory {

    private Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private AutowireCapableBeanFactory beanFactory;

    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        Object jobInstance = super.createJobInstance(bundle);
        beanFactory.autowireBean(jobInstance);
        log.debug("构建定时任务={}",jobInstance);
        return jobInstance;
    }
}