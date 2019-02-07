package cn.inkroom.log.master.socket;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author 墨盒
 * @date 18-12-10
 */
public class MessageHandler extends ChannelInboundHandlerAdapter {

    private Logger logger = LoggerFactory.getLogger(getClass());



    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf buf = (ByteBuf) msg;
        try {
            byte[] req = new byte[buf.readableBytes()];
            buf.readBytes(req);
            String result = new String(req, "UTF-8");
            logger.debug("接收到的消息={}", result);
        } finally {
            buf.release();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        logger.error(cause.getMessage(), cause);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);

        logger.debug(" public void channelRegistered(ChannelHandlerContext ctx) throws Exception {");

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        logger.debug("public void channelActive(ChannelHandlerContext ctx) throws Exception");

    }

}
