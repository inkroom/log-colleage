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
package cn.inkroom.log.web.service;

import cn.inkroom.log.web.bean.DownloadLog;
import cn.inkroom.log.web.bean.LoginLog;
import cn.inkroom.log.web.dao.DownloadLogDao;
import cn.inkroom.log.web.dao.LoginLogDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 墨盒
 * @date 19-4-30
 */
@Service
public class DownloadLogService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DownloadLogDao dao;

    public List<LoginLog> loginLogs() {
        try {
            return dao.selectLog();
        } catch (Exception e) {
            logger.error("获取登录日志", e);
            return null;
        }
    }

    public int insertLog(DownloadLog log) {
        try {
            return dao.insertLog(log);
        } catch (Exception e) {
            logger.error("添加下载记录失败", e);
            return -1;
        }
    }

}
