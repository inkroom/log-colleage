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
package cn.inkroom.log.web.check;

import cn.inkroom.log.model.LogMsg;

/**
 * 检测日志，并作出响应的操作
 *
 * @author 墨盒
 * @date 19-5-6
 */
public interface CheckLog {
    /**
     * 检测日志消息
     *
     * @param msg 日志
     * @return null为正常日志，其余为要发送的消息
     */
    String checkLog(LogMsg msg);

}
