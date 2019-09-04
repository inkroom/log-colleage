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
