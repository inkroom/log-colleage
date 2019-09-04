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
package cn.inkroom.log.quartz;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 墨盒
 * @date 19-1-11
 */
@Component
public class SchedulerManager {

    private Logger log = LoggerFactory.getLogger(getClass());
    private Scheduler scheduler;
    //    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    private ArrayList<Map<String, String>> keys;

    private static final String KEY_NAME = "name";
    private static final String KEY_GROUP = "group";

    @Autowired
    public SchedulerManager(AutowireSpringBeanJobFactory beanJobFactory) {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        bean.setJobFactory(beanJobFactory);
        bean.setAutoStartup(true);
        try {
            bean.afterPropertiesSet();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.schedulerFactoryBean = bean;
        scheduler = schedulerFactoryBean.getScheduler();
        keys = new ArrayList<>();
        log.debug("构造定时任务管理器");
    }


    /**
     * 添加任务
     *
     * @param c     对应的class
     * @param name  name
     * @param group group
     * @param cron  cron表达式
     */
    public void addJob(Class<? extends Job> c, String name, String group, String cron) {

        if (scheduler == null)
            scheduler = schedulerFactoryBean.getScheduler();

//        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
//        jobDetailFactoryBean.setJobClass(c);
//        jobDetailFactoryBean.setDurability(true);
//        jobDetailFactoryBean.setGroup(group);
//        jobDetailFactoryBean.setName(name);
////            jobDetailFactoryBean.setApplicationContextJobDataKey("123");
////            jobDetailFactoryBean.setRequestsRecovery(true);
//        jobDetailFactoryBean.afterPropertiesSet();
////
//        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
////            cronTriggerFactoryBean.setJobDetail(jobDetailFactoryBean.getObject());
//        cronTriggerFactoryBean.setCronExpression(cron);
//        cronTriggerFactoryBean.setName("trigger Name");

        try {
//            cronTriggerFactoryBean.afterPropertiesSet();

            log.debug("下次执行时间={}", scheduler.scheduleJob(createJobDetail(name, group, c), createCronTrigger(name, group, cron)));
            scheduler.start();
            log.info("添加任务 name = " + name + "  group = " + group + "  class = " + c.getName());
//scheduler.get
            addKeys(name, group);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JobDetail createJobDetail(String name, String group, Class<? extends Job> c) {
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(c);
        jobDetailFactoryBean.setDurability(true);
        jobDetailFactoryBean.setGroup(group);
        jobDetailFactoryBean.setName(name);
//            jobDetailFactoryBean.setApplicationContextJobDataKey("123");
//            jobDetailFactoryBean.setRequestsRecovery(true);
        jobDetailFactoryBean.afterPropertiesSet();
        return jobDetailFactoryBean.getObject();
    }

    private Trigger createCronTrigger(String name, String group, String cron) {

        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
//            cronTriggerFactoryBean.setJobDetail(jobDetailFactoryBean.getObject());
        cronTriggerFactoryBean.setCronExpression(cron);
        cronTriggerFactoryBean.setName(name);
        cronTriggerFactoryBean.setGroup(group);
        try {
            cronTriggerFactoryBean.afterPropertiesSet();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cronTriggerFactoryBean.getObject();
    }

    public void deleteJob(String name, String group) {
        try {
            log.info("删除任务 name = " + name + "  group = " + group + "  result = " + scheduler.deleteJob(JobKey.jobKey(name, group)));
            deleteKeys(name, group);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    private void addKeys(String name, String group) {
        Map<String, String> map = new HashMap<>();
        map.put(KEY_NAME, name);
        map.put(KEY_GROUP, group);

        keys.add(map);
    }

    private void deleteKeys(String name, String group) {
        for (Map<String, String> map : keys) {
            if (map.get(KEY_NAME).equals(name) && map.get(KEY_GROUP).equals(group)) {
                keys.remove(map);
                break;
            }
        }
    }

    public void updateCron(String name, String group, String cron) {
        try {
//            Trigger trigger = scheduler.getTrigger(TriggerKey.triggerKey(name, group));
            JobDetail jobDetail = scheduler.getJobDetail(JobKey.jobKey(name, group));
            Class<? extends Job> c = jobDetail.getJobClass();
//            JobDetail jobDetail = scheduler.getJobDetail(JobKey.jobKey(name, group));
//            deleteJob(name, group);
            log.info("更新任务 name = " + name + "  group = " + group);
            deleteJob(name, group);
            addJob(c, name, group, cron);
//            scheduler.rescheduleJob(TriggerKey.triggerKey(name, group), trigger);

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public boolean exists(String name, String group) {
        try {
            return scheduler.checkExists(JobKey.jobKey(name, group));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void standBy(String name, String group) {
        try {
            scheduler.pauseJob(JobKey.jobKey(name, group));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void resume(String name, String group) {
        try {
            scheduler.resumeJob(JobKey.jobKey(name, group));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void resume() {
        try {
            scheduler.resumeAll();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }


    public List<JobDetail> getAllJob() {
        ArrayList<JobDetail> details = new ArrayList<>(keys.size());
        for (Map<String, String> map : keys) {
            try {
                details.add(scheduler.getJobDetail(JobKey.jobKey(map.get(KEY_NAME), map.get(KEY_GROUP))));
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
        }
        return details;
//        scheduler.
    }

    public List<Trigger> getAllTrigger() {
        ArrayList<Trigger> details = new ArrayList<>(keys.size());
        for (Map<String, String> map : keys) {
            try {
                details.add(scheduler.getTrigger(TriggerKey.triggerKey(map.get(KEY_NAME), map.get(KEY_GROUP))));
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
        }
        return details;
    }
}
