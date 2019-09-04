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
package cn.inkroom.log.server.dao;

import cn.inkroom.log.model.LogMsg;

import java.util.List;

/**
 * @author 墨盒
 * @date 19-1-8
 */
public interface LogDao {

    /**
     * 入库
     *
     * @param msg 日志消息
     * @return 受影响行数
     * @throws Exception
     */
    int insert(LogMsg msg) throws Exception;

    /**
     * 获取指定时间范围内的数据
     *
     * @param start 开始时间戳
     * @param end   结束时间戳
     * @return 日志消息集合
     * @throws Exception
     */
    List<LogMsg> selectByTime(long start, long end) throws Exception;
}
