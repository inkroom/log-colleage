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

import cn.inkroom.log.web.bean.MessageDto;
import cn.inkroom.log.web.bean.Statistics;
import cn.inkroom.log.web.service.StatisticsService;
import cn.inkroom.log.web.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * @author 墨盒
 * @date 19-2-18
 */
@Controller
public class StatisticsController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    private StatisticsService service;

    @GetMapping("statistics")
    public String index() throws RuntimeException {

        request.setAttribute("tags", service.getTags());
        request.setAttribute("ips", service.getIP());

        return "statistics";
    }

    @PostMapping("statistics")
    @ResponseBody
    public MessageDto<List<Statistics>> list(String tag, String ip, @RequestParam(defaultValue = "-1") int level, String start, String end) {

        // TODO: 19-5-31 由于spring默认拿不到post json数据，因此DateConvert不被调用

        //处理可能存在的空字符
        tag = StringUtils.isEmpty(tag) ? null : tag;
        ip = StringUtils.isEmpty(ip) ? null : ip;


        logger.debug("开始时间{}", start);
        logger.debug("结束时间{}", end);

        List<Statistics> loginLogs = service.list(tag, ip, level,
                StringUtils.isEmpty(start) ? null : DateUtils.parse(start, "yyyy-MM-dd HH:mm"),
                StringUtils.isEmpty(end) ? null : DateUtils.parse(end, "yyyy-MM-dd HH:mm"),
                1, 10);
        if (loginLogs == null) return new MessageDto<>(1);
        return new MessageDto<>(0, loginLogs);
    }

}
