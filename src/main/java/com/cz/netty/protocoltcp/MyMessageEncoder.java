package com.cz.netty.protocoltcp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MyMessageEncoder extends MessageToByteEncoder<MessageProtocol> {
    @Override
    protected void encode(ChannelHandlerContext ctx, MessageProtocol msg, ByteBuf byteBuf) throws Exception {
        System.out.println("MyMessageEncoder encode has been invoke...");
        byteBuf.writeInt(msg.getLen());
        byteBuf.writeBytes(msg.getContent());
    }
}
