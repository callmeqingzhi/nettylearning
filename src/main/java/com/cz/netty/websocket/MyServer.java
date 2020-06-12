package com.cz.netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @Description TODO
 * @Author cz
 * @Date 2020/6/12 11:27
 **/
public class MyServer {
    public static void main(String[] args) throws Exception {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).handler(new LoggingHandler(LogLevel.INFO)).childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();
                    // 因为基于http协议，所以添加http编解码器
                    pipeline.addLast(new HttpServerCodec());
                    // 是以块的方式写，添加ChunkedWriteHandler
                    pipeline.addLast(new ChunkedWriteHandler());
                    // http数据在传输过程中是分段的，HttpObjectAggregator可以将多个段聚合
                    // 当浏览器发送大量数据时，就会发出多次请求
                    pipeline.addLast(new HttpObjectAggregator(8192));// 8kb
                    /**
                     * 对于websocket,他的数据是以帧形式传递的
                     * 可以看到webSocketFrame 下面有六个子类
                     * 浏览器请求时 ws://localhost:7000/hello 表示请求的uri
                     * WebSocketServerProtocolHandler 核心功能是将http协议升级为ws协议，保持长连接
                     * 是通过一个状态码 101 切换协议，如上图
                     * */
                    pipeline.addLast(new WebSocketServerProtocolHandler("/hello"));
                    // 自定义handler，处理业务逻辑
                    pipeline.addLast(new MyTextWebSocketFrameHandler());
                }
            });
            ChannelFuture channelFuture = bootstrap.bind(7000).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}