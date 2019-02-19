package quartz;

import base.BaseTest;
import cn.inkroom.log.server.quartz.job.backup.BackupJob;
import org.junit.Test;
import org.quartz.JobExecutionContext;
import org.quartz.impl.JobExecutionContextImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

/**
 * @author 墨盒
 * @date 19-2-19
 */
public class BackupTest extends BaseTest {

    @Autowired
    private AutowireCapableBeanFactory beanFactory;

    @Test
    public void testBackup() throws Exception {

        BackupJob job = new BackupJob();
        beanFactory.autowireBean(job);

        job.executeInternal(null);


    }
}
