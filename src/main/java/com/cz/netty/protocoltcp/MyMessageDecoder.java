package com.cz.netty.protocoltcp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

public class MyMessageDecoder extends ReplayingDecoder<Void> {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        System.out.println("MyMessageDecoder decode has been invoke...");

        // 将二进制字节码->MessageProtocol
        int length = byteBuf.readInt();
        byte[] content = new byte[length];
        byteBuf.readBytes(content);
        MessageProtocol messageProtocol = new MessageProtocol();
        messageProtocol.setLen(length);
        messageProtocol.setContent(content);
        // 放入到list中，传递给下一个handler处理
        list.add(messageProtocol);
    }
}
