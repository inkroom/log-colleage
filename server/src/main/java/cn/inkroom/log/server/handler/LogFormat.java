package cn.inkroom.log.server.handler;

import cn.inkroom.log.model.LogMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.rmi.runtime.Log;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 日志消息格式化成单行文本
 *
 * @author 墨盒
 * @Date 19-1-11
 */
public class LogFormat {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 每行的数据格式
     * {m} 消息
     * {i} ip
     * {t} time
     * {tag} tags
     */
    private String format;

    private Pattern pattern;

    private DateFormat dateFormat;

    public LogFormat() {

        format = "{t} {tag} {i} : {m}";


        pattern = Pattern.compile("\\{([^\\}]+)\\}");
    }

    public void setDateFormat(String dateFormat) {

        this.dateFormat = new SimpleDateFormat(dateFormat);
    }

    public void setFormat(String format) {
        this.format = format;
    }


    public String format(LogMsg log) {

        // TODO: 19-1-11 目前实现并不好，可以参照log4j等源码查看如何拼接
//        StringBuilder builder = new StringBuilder(format);
        Matcher matcher = pattern.matcher(format);

        List<String> list = new LinkedList<>();

        int i = 0;
        while (matcher.find()) {
            String key = matcher.group(1);
            switch (key) {
                case "t":
                    list.add(log.getTime() == null ? "" : (dateFormat == null ? log.getTime().toString() : dateFormat.format(log.getTime())));
                    break;
                case "tag":
                    list.add(log.getTag() == null ? "" : log.getTag());
                    break;
                case "i":
                    list.add(log.getIp() == null ? "" : log.getIp());
                    break;
                case "m":
                    list.add(log.getMsg() == null ? "" : log.getMsg());
                    break;
            }
            i++;


        }
        return String.format(format.replaceAll("\\{[^\\}]+\\}", "%s"), list.toArray());
//        return null;

    }
}
