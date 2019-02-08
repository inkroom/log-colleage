package cn.inkroom.log.server.socket;

import cn.inkroom.log.server.bean.Socket;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * socket连接
 *
 * @author 墨盒
 * @date 18-12-11
 */
public class SocketConnector {

    private Logger logger = LoggerFactory.getLogger(getClass());


    public SocketConnector(Socket socket) throws Exception {
        // Configure the client.
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true).handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
//                    ch.pipeline().addLast(
//                            // new LoggingHandler(LogLevel.INFO),
//                            new SocketHandler());
                }
            });

            // Start the client.
            ChannelFuture f = b.connect(socket.getHost(), socket.getPort()).sync();

            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
        } finally {
            // Shut down the event loop to terminate all threads.
            group.shutdownGracefully();
        }
    }


}
