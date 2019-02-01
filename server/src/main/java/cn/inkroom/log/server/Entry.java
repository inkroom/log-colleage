package cn.inkroom.log.server;

import cn.inkroom.log.server.bean.Socket;
import cn.inkroom.log.server.socket.SocketConnector;
import cn.inkroom.log.server.socket.SocketHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
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


        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:application.xml");
    }
}
