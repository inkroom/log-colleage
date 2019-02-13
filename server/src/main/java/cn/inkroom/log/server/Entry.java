package cn.inkroom.log.server;

import cn.inkroom.log.server.bean.Socket;
import cn.inkroom.log.server.socket.SocketConnector;
import cn.inkroom.log.server.socket.SocketHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;

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
//       NetServer server= vertx.createNetServer();
//
//        server.connectHandler(socket -> {
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
