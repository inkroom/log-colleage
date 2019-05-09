package cn.inkroom.log.web.controller;

import cn.inkroom.log.model.LogBackup;
import cn.inkroom.log.web.bean.DownloadLog;
import cn.inkroom.log.web.bean.MessageDto;
import cn.inkroom.log.web.config.Asserts;
import cn.inkroom.log.web.service.DownloadLogService;
import cn.inkroom.log.web.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private HttpServletResponse response;
    @Autowired
    private FileService fileService;
    @Autowired
    private DownloadLogService downloadLogService;

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



    @RequestMapping("download")
    @ResponseBody
    public void download(String name, String ip) throws IOException {
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(ip)) {

            return;
        }


        InputStream inputStream = fileService.download(name, ip);
        if (inputStream == null) {
            response.sendError(404);
        }

        List<LogBackup> backups = fileService.list(ip);

        Pattern pattern = Pattern.compile("/"+name.replace(".","\\.")+"$");
        for (LogBackup backup : backups) {
            if (pattern.matcher(backup.getPath()).find()) {
                DownloadLog log = new DownloadLog();
                log.setFile(name);
                log.setUsername(request.getSession().getAttribute(Asserts.AUTH_SESSION_KEY).toString());
                log.setStart(backup.getStart());
                log.setEnd(backup.getEnd());
                log.setSize(backup.getSize());

                downloadLogService.insertLog(log);

                break;
            }
        }


        response.addHeader("Content-Disposition", "attachment;filename=" + new String((ip + "_" + name).getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));


        OutputStream output = response.getOutputStream();
        byte[] buff = new byte[1024 * 10];//可以自己 指定缓冲区的大小
        int len = 0;
        while ((len = inputStream.read(buff)) > -1) {
            output.write(buff, 0, len);
        }
        //关闭输入输出流
        inputStream.close();
        output.close();
    }

}
