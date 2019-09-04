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
package cn.inkroom.log.web.dao;

import cn.inkroom.log.web.bean.LoginLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 登录日志
 *
 * @author 墨盒
 * @date 19-4-30
 */
public interface LoginLogDao {
    /**
     * 记录登录日志
     *
     * @param ip      ip地址
     * @param ua      使用的浏览器
     * @param account 登录的账号
     * @return
     * @throws Exception
     */
    int insertLog(@Param("l") LoginLog log) throws Exception;

    /**
     * 获取登录日志
     *
     * @return
     * @throws Exception
     */
    List<LoginLog> selectLog() throws Exception;
}
