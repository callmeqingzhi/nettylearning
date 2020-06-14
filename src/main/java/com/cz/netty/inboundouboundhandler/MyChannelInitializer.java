package com.cz.netty.inboundouboundhandler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;


public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {

        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new MyByte2LongDecoder2());
        pipeline.addLast(new MyLong2ByteEncoder());
        // 自定义handler 处理业务逻辑
        pipeline.addLast(new MyLongHandler());

    }
}
