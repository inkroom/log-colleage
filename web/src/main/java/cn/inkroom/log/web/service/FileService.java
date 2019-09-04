/**
 * Copyright © 2019 inkbox (enpassPixiv@protonmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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


        //连接socket

        try {
            Socket socket = new Socket(ip, getPort(ip));

            socket.setSoTimeout(10000);
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

            for (int i = 0; i < array.size(); i++) {
                LogBackup file = JSON.parseObject(array.getString(i), LogBackup.class);

                files.add(file);
            }
            socket.close();
            return files;

        } catch (IOException e) {
            logger.warn("获取文件列表失败", e);
        }

        return null;

    }

    private int getPort(String ip) {
        List<Server> servers = serverService.getServerList();
        for (Server server : servers) {
            if (server.getIp().equals(ip)) {
                return server.getFilePort();
            }
        }
        return -1;
    }

    public InputStream download(String path, String ip) {

        int port = getPort(ip);
        if (port == -1) return null;

        try {
            Socket socket = new Socket(ip, port);

//            socket.setSoTimeout(10000);

            socket.setKeepAlive(true);

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));{

                writer.write(path);
                writer.flush();
                return socket.getInputStream();
            }
        } catch (IOException e) {
            logger.error("下载日志文件失败", e);
        }
        return null;
    }
}
