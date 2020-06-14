package com.cz.netty.inboundouboundhandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

public class MyByte2LongDecoder2 extends ReplayingDecoder<Void> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list)
            throws Exception {
        System.out.println("MyByte2LongDecoder2 decode has been invoked...");
        // ReplayingDecoder 自行判断，这里无需手动判断是否够8个字节
//        if (byteBuf.readableBytes()>=8)
        list.add(byteBuf.readLong());
    }
}
