package cn.inkroom.log.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 墨盒
 * @date 19-2-7
 */
@Controller
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private HttpServletRequest request;

    @RequestMapping("index")
    public String index() throws RuntimeException {
        return "index";
    }

}
