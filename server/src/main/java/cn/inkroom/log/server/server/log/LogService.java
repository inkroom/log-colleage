package cn.inkroom.log.server.server.log;

import cn.inkroom.log.model.LogMsg;
import cn.inkroom.log.server.dao.LogDao;
import cn.inkroom.log.server.handler.LogFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

/**
 * 日志的备份等服务
 *
 * @author 墨盒
 * @date 19-1-11
 */

@Service

public class LogService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private LogDao dao;

    private LogFormat format = new LogFormat();//格式

    @Autowired(required = false)
    public void setFormat(LogFormat format) {
        this.format = format;
    }

    /**
     * 备份日志到文件
     *
     * @param start 开始时间
     * @param end   结束时间
     * @param path  存储的文件绝对路径
     * @return 记录的日志条数
     * @throws Exception
     */
    public int backup(long start, long end, String path) throws Exception {


        // TODO: 19-1-11 暂时先用bio，后期再改成nio

//读取数据
        List<LogMsg> msgs = dao.selectByTime(start, end);


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (LogMsg msg : msgs) {
                writer.write(format.format(msg));
                writer.newLine();
            }
        }
        return msgs.size();

    }


}
