package cn.inkroom.log.server.socket;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.buffer.UnpooledDirectByteBuf;
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

/**
 * @author 墨盒
 * @date 18-12-11
 */
public class SocketHandler extends ChannelInboundHandlerAdapter implements Handler<Buffer> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public static Channel channel;

    private NetSocket socket;


    public SocketHandler(int port) {

        logger.debug("监听socket端口={}", port);

        Vertx vertx = Vertx.vertx();
        NetServer server = vertx.createNetServer();
        server.connectHandler(new Handler<NetSocket>() {
            @Override
            public void handle(NetSocket socket) {
                SocketHandler.this.socket = socket;
                socket.handler(SocketHandler.this);
            }
        }).listen(port);
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        ByteBuf buf = Unpooled.buffer(100);
//String msg = "新消息";
//        ByteBufUtil.writeAscii(buf,msg);
////        for (int i = 0; i < msg.length(); i++) {
////            buf.writeByte(msg.charAt(i));
////        }
//        ctx.writeAndFlush(buf);
////        ctx.channel().write("新消息");
////        ctx.channel().flush();


        byte[] data = "服务器，给我一个APPLE".getBytes();
        buf = Unpooled.buffer();
        buf.writeBytes(data);
        ctx.writeAndFlush(buf);
        synchronized (SocketHandler.class) {
            channel = ctx.channel();
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }


    @Override
    public void handle(Buffer buffer) {

        String filename = buffer.toString();
        logger.debug("文件名={}", buffer.toString());

        socket.sendFile(filename);

    }
}
