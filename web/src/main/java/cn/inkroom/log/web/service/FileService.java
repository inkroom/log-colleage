package cn.inkroom.log.web.service;

import cn.inkroom.log.model.LogBackup;
import cn.inkroom.log.model.Server;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * 负责处理server保存的文件
 *
 * @author 墨盒
 * @date 19-2-18
 */
@Service
public class FileService {
    @Autowired
    private ServerService serverService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public List<LogBackup> list(String ip) {

        List<Server> servers = serverService.getServerList();
        for (int i = 0; i < servers.size(); i++) {
            if (servers.get(i).getIp().equals(ip)) {

                Server server = servers.get(i);

                //连接socket

                try {
                    Socket socket = new Socket(ip, server.getFilePort());

//                    socket.setSoTimeout(10000);
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    writer.write("list");
                    writer.flush();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    //读取列表
                    String message = reader.readLine();
                    logger.debug("获取的文件列表={}", message);
                    //转换
                    JSONArray array = JSONArray.parseArray(message);

                    List<LogBackup> files = new ArrayList<>();

                    for (int j = 0; j < array.size(); j++) {
                        LogBackup file = JSON.parseObject(array.getString(i), LogBackup.class);

                        files.add(file);
                    }
                    socket.close();
                    return files;

                } catch (IOException e) {
                    logger.warn("获取文件列表失败", e);
                }
            }
        }

        return null;

    }
}
