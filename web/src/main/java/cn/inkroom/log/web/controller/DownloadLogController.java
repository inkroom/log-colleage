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
package cn.inkroom.log.web.controller;

import cn.inkroom.log.web.bean.LoginLog;
import cn.inkroom.log.web.bean.MessageDto;
import cn.inkroom.log.web.service.DownloadLogService;
import cn.inkroom.log.web.service.LoginLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 墨盒
 * @date 19-2-18
 */
@Controller
public class DownloadLogController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    private DownloadLogService service;

    @GetMapping("download_log")
    public String index() throws RuntimeException {
        return "download_log";
    }

    @PostMapping("download_log")
    @ResponseBody
    public MessageDto<List<LoginLog>> list() {

        List<LoginLog> loginLogs = service.loginLogs();
        if (loginLogs == null) return new MessageDto<>(1);
        return new MessageDto<>(0, loginLogs);
    }

}
