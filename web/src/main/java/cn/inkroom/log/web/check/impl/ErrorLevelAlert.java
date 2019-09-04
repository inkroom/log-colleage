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
package cn.inkroom.log.web.check.impl;

import cn.inkroom.log.model.LogMsg;
import cn.inkroom.log.web.check.CheckLog;

/**
 * error级别日志报警
 *
 * @author 墨盒
 * @date 19-5-6
 */
public class ErrorLevelAlert implements CheckLog {
    @Override
    public String checkLog(LogMsg msg) {
        //TODO 目前使用log4j的日志级别,需要一个可以兼容多数日志框架的判断方式
        if (msg.getLevel() == 40000) {


            return msg.getTag() + " 发生错误，<br/>日志时间=" + msg.getTime() + " <br/>错误机器为： " + msg.getIp() + " <br/>错误信息为：" + msg.getMsg();

        }
        return null;

    }

}
