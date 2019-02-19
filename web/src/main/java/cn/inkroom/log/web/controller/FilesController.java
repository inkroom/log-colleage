package cn.inkroom.log.web.controller;

import cn.inkroom.log.model.LogBackup;
import cn.inkroom.log.web.bean.MessageDto;
import cn.inkroom.log.web.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

/**
 * @author 墨盒
 * @date 19-2-18
 */
@Controller
public class FilesController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private FileService fileService;

    @RequestMapping("files")
    public String index() throws RuntimeException {
        return "files";
    }

    @RequestMapping("fileList")
    @ResponseBody
    public MessageDto<List<LogBackup>> list(String ip) throws RuntimeException {
        if (StringUtils.isEmpty(ip)) {

            return new MessageDto<>(3);
        }

        List<LogBackup> files = fileService.list(ip);
        if (files != null) {
            return new MessageDto<>(0, files);
        } else {
            return new MessageDto<>(1);
        }

    }

}
