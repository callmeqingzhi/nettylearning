package com.cz.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class TestServerInitializer extends ChannelInitializer<SocketChannel> {

    protected void initChannel(SocketChannel socketChannel) throws Exception {
        // 向管道加入处理器

        // 得到管道
        ChannelPipeline pipeline = socketChannel.pipeline();
        // 加入一个netty提供的httpServerCodec (http的编解码器) 加入一个自定义的handler
        pipeline.addLast("myCodec", new HttpServerCodec());
        pipeline.addLast("myHandler", new TestHttpServerHandler());

    }
}
