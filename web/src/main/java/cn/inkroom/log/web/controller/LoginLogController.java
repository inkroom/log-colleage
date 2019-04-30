package cn.inkroom.log.web.controller;

import cn.inkroom.log.web.bean.LoginLog;
import cn.inkroom.log.web.bean.MessageDto;
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
public class LoginLogController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    private LoginLogService service;

    @GetMapping("login_log")
    public String index() throws RuntimeException {
        return "login_log";
    }

    @PostMapping("login_log")
    @ResponseBody
    public MessageDto<List<LoginLog>> list() {

        List<LoginLog> loginLogs = service.loginLogs();
        if (loginLogs == null) return new MessageDto<>(1);
        return new MessageDto<>(0, loginLogs);
    }

}
