package cn.inkroom.log.web.controller;

import cn.inkroom.log.web.bean.DownTime;
import cn.inkroom.log.web.bean.MessageDto;
import cn.inkroom.log.web.service.DownTimeService;
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
public class DownTimeController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    private DownTimeService service;

    @GetMapping("downtime")
    public String index() throws RuntimeException {
        return "downtime";
    }

    @PostMapping("downtime")
    @ResponseBody
    public MessageDto<List<DownTime>> list() {

        List<DownTime> loginLogs = service.getDwonTime(null, null);
        if (loginLogs == null) return new MessageDto<>(1);
        return new MessageDto<>(0, loginLogs);
    }

}
