package com.something.netty;

import com.something.netty.util.MarshallingCodeCFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.marshalling.MarshallingDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author cWww
 * @Title ServerDemo
 * @Description 服务端
 * @date: 2018/9/29  19:02
 */
public class ServerDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServerDemo.class);

    public static void main(String[] args) throws Exception {

        //线程组：用来处理网络事件处理（接受客户端连接）
        final EventLoopGroup pgroup = new NioEventLoopGroup();
        //线程组：用来进行网络通讯读写
        final EventLoopGroup cgroup = new NioEventLoopGroup();

        final ServerBootstrap b = new ServerBootstrap()
                                    .group(pgroup,cgroup)
                                    .channel(NioServerSocketChannel.class)
        /**
         *  BACKLOG用于构造服务端套接字ServerSocket对象，标识当服务器请求处理线程全满时，
         *  用于临时存放已完成三次握手的请求的队列的最大长度。如果未设置或所设置的值小于1，将使用默认值50。
         *  服务端处理客户端连接请求是顺序处理的，所以同一时间只能处理一个客户端连接，多个客户端来的时候，
         *  服务端将不能处理的客户端连接请求放在队列中等待处理，backlog参数指定了队列的大小
         */
                                    .option(ChannelOption.SO_BACKLOG,1024)
                                    //设置日志
                                    .handler(new LoggingHandler(LogLevel.INFO))
                                    .childHandler(new ChannelInitializer<SocketChannel>() {
                                        @Override
                                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                                            //marshaliing的编解码操作,要传输对象，必须编解码
                                            socketChannel.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
                                            socketChannel.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
                                            //5s没有交互，就会关闭channel
                                            socketChannel.pipeline().addLast(new ReadTimeoutHandler(5));
                                            //服务端业务处理类
                                            socketChannel.pipeline().addLast(new ServerHandler());

                                        }
                                    });

        final ChannelFuture cf = b.bind(8765).sync();
        cf.channel().closeFuture().sync();
        pgroup.shutdownGracefully();
        cgroup.shutdownGracefully();
    }

}
