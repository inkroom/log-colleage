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
package cn.inkroom.log.server.socket;

import cn.inkroom.log.server.service.BackupService;
import com.alibaba.fastjson.JSON;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetServer;
import io.vertx.core.net.NetSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;

/**
 * @author 墨盒
 * @date 18-12-11
 */
public class SocketHandler extends ChannelInboundHandlerAdapter implements Handler<Buffer> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public static Channel channel;

    private NetSocket socket;
    @Autowired
    private BackupService backupService;

    @Value("${quartz.backup.dir}")
    private String backupDir;

    public SocketHandler(int port) {

        logger.debug("监听socket端口={}", port);

        Vertx vertx = Vertx.vertx();
        NetServer server = vertx.createNetServer();
        server.connectHandler(socket -> {
            SocketHandler.this.socket = socket;
            socket.handler(SocketHandler.this);
        }).listen(port);
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        logger.debug("socket被连接");
//        ByteBuf buf = Unpooled.buffer(100);
//String msg = "新消息";
//        ByteBufUtil.writeAscii(buf,msg);
////        for (int i = 0; i < msg.length(); i++) {
////            buf.writeByte(msg.charAt(i));
////        }
//        ctx.writeAndFlush(buf);
////        ctx.channel().write("新消息");
////        ctx.channel().flush();


//        byte[] data = "服务器，给我一个APPLE".getBytes();
//        buf = Unpooled.buffer();
//        buf.writeBytes(data);
//        ctx.writeAndFlush(buf);
//        synchronized (SocketHandler.class) {
//            channel = ctx.channel();
//        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }


    @Override
    public void handle(Buffer buffer) {

        String filename = buffer.toString();

        logger.debug(filename);
        logger.debug("判断结果={}", "list".equals(filename));
        logger.debug("判断结果={}", filename.equals("list"));
        if ("list".equals(filename)) {
            logger.info("开始获取备份文件列表");
            logger.debug("备份文件列表={}", backupService.list());
            //获取文件列表
            socket.write(JSON.toJSONString(backupService.list()));
            socket.end();
//            socket.fetch()
            return;
        }
        logger.debug("文件路径={}", backupDir + File.separator + filename);
        socket.sendFile(backupDir + File.separator + filename);
        socket.close();
    }
}
