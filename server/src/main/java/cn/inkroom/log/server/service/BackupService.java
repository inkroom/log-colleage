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
package cn.inkroom.log.server.service;

import cn.inkroom.log.model.LogBackup;
import cn.inkroom.log.model.LogMsg;
import cn.inkroom.log.server.dao.LogDao;
import cn.inkroom.log.server.dao.h2.BackupDao;
import cn.inkroom.log.server.handler.LogFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

/**
 * @author 墨盒
 * @date 19-2-18
 */
@Service
public class BackupService {

    @Autowired
    private BackupDao dao;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private LogDao logDao;


    private LogFormat format = new LogFormat();//格式

    public BackupService() {
        logger.debug("创建备份服务");
    }

    @Autowired(required = false)
    public void setFormat(LogFormat format) {
        this.format = format;
    }

    public List<LogBackup> list() throws RuntimeException {
        try {
            List<LogBackup> backups = dao.selectAll();
            logger.debug("dao层获取的备份文件列表={}", backups);
            return backups;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 备份日志到文件
     *
     * @param start 开始时间
     * @param end   结束时间
     * @param path  存储的文件绝对路径
     * @return 记录的日志条数
     * @throws Exception
     */
    public int backup(long start, long end, String path) throws Exception {


        // TODO: 19-1-11 暂时先用bio，后期再改成nio

        //读取数据
        List<LogMsg> msgs = logDao.selectByTime(start, end);
        if (msgs == null) {
            return 0;
        }
        File file = new File(path);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (LogMsg msg : msgs) {
                writer.write(format.format(msg));
                writer.newLine();
            }
        }

        //记录备份时间范围


        return msgs.size();

    }
}
