package cn.inkroom.log.web.controller;

import cn.inkroom.log.web.bean.MessageDto;
import cn.inkroom.log.web.bean.Statistics;
import cn.inkroom.log.web.service.StatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public MessageDto<List<Statistics>> list(String tag, String ip, @RequestParam(defaultValue = "-1") int level, Date start, Date end) {

        //处理可能存在的空字符
        tag = StringUtils.isEmpty(tag) ? null : tag;
        ip = StringUtils.isEmpty(ip) ? null : ip;

        List<Statistics> loginLogs = service.list(tag, ip, level,start,end, 1, 10);
        if (loginLogs == null) return new MessageDto<>(1);
        return new MessageDto<>(0, loginLogs);
    }

}
