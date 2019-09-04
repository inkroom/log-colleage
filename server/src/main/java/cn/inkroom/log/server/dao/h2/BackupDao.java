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
package cn.inkroom.log.server.dao.h2;

import cn.inkroom.log.model.LogBackup;

import java.util.List;

/**
 * 用于存储日志文件路径等数据<br>
 * 使用h2数据库
 *
 * @author 墨盒
 * @date 19-2-8
 */
public interface BackupDao {

    int insertFile(LogBackup file) throws Exception;

    /**
     * 筛选出最新一条记录
     *
     * @return 记录
     * @throws Exception 异常
     */
    LogBackup selectLast() throws Exception;

    /**
     * 获取所有备份记录
     * @return
     * @throws Exception
     */
    List<LogBackup> selectAll() throws Exception;
}
