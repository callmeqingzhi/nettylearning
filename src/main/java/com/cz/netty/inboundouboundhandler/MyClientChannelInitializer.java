package com.cz.netty.inboundouboundhandler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class MyClientChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        // 出站从尾部开始处理，所以MyClientHandler先执行 MyLong2ByteEncoder后执行
        pipeline.addLast(new MyLong2ByteEncoder());
        pipeline.addLast(new MyByte2LongDecoder());
        pipeline.addLast(new MyClientHandler());

    }
}
