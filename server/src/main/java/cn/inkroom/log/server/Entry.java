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
package cn.inkroom.log.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 墨盒
 * @date 18-11-20
 */
public class Entry {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public static void main(String[] args) {


//        File file = new File("conf/config.properties");




//        Socket socket = new Socket();
//        socket.setHost("127.0.0.1");
//        socket.setPort(8089);
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    SocketConnector connector = new SocketConnector(socket);
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//
//        synchronized (SocketHandler.class) {
//            while (SocketHandler.channel != null) {//等待连接完成
//                byte[] data = "第二条消息".getBytes();
//                ByteBuf buf = Unpooled.buffer();
//                buf.writeBytes(data);
//                SocketHandler.channel.write(buf);
//                SocketHandler.channel.flush();
//            }
//
//        }

//        Vertx vertx = Vertx.vertx();
//
//       NetServer service= vertx.createNetServer();
//
//        service.connectHandler(socket -> {
//
//            socket.handler(buffer -> {
//                //获取对于的文件名
//                System.out.println("收到的消息-"+buffer.toString());
//                socket.sendFile("/media/inkbox/study/娱乐/图片/GOSICK/GOSICK09.jpg");
//                socket.end();
//            });
//
//        }).listen(9876, netServerAsyncResult -> System.out.println("监听启动"));
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:application.xml");
    }
}
