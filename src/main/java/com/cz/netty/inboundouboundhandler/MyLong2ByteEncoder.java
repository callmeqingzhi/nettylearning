package com.cz.netty.inboundouboundhandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MyLong2ByteEncoder extends MessageToByteEncoder<Long> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Long aLong, ByteBuf byteBuf) throws Exception {
        System.out.println("MyLong2ByteEncoder encode has been invoked...");
        System.out.println("along= " + aLong);
        byteBuf.writeLong(aLong);
    }
}
