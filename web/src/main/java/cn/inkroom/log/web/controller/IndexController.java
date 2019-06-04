package cn.inkroom.log.web.controller;

import cn.inkroom.log.model.Server;
import cn.inkroom.log.web.bean.MessageDto;
import cn.inkroom.log.web.handler.PropertiesHandler;
import cn.inkroom.log.web.service.ServerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 墨盒
 * @date 19-2-7
 */
@Controller
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ServerService serverService;

    @RequestMapping({"/index","/"})
    public String index() throws RuntimeException {

        logger.debug("{}", PropertiesHandler.getProperties());
        logger.debug("属性 active.profile={}", System.getProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME));
        logger.debug("属性 default.profile={}", System.getProperty(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME));

        return "index";
    }

    @RequestMapping("list")
    @ResponseBody
    public MessageDto<List<Server>> list() throws RuntimeException {
        List<Server> list = serverService.getServerList();
        return new MessageDto<>(0, list);
    }

    @RequestMapping("rm")
    @ResponseBody
    public MessageDto<Server> rm(String ip) throws RuntimeException {

        if (ip == null || ip.isEmpty()) {
            return new MessageDto<>(3);
        }
        return new MessageDto<>(serverService.deleteServer(ip) == 1 ? 0 : 1);
    }

}
