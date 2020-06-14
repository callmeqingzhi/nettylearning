package com.cz.netty.inboundouboundhandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * 自定义解码器，从byte转为long类型
 */
public class MyByte2LongDecoder extends ByteToMessageDecoder {

    /**
     * decode会根据接收的数据，被调用多次，直到确定没有新的元素被添加到list
     * ，或者是byteBuf没有可读数据为止，如果list的内容不为空，则会把list传递给下一个channelInBoundHandler处理
     * ，该处理器的对应方法也会被调用多次
     * @param channelHandlerContext 上下文
     * @param byteBuf 入站数据
     * @param list list集合，将解码后的数据传递给下一个handler
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list)
            throws Exception {
        System.out.println("MyByte2LongDecoder decode has been invoked...");
        // 够8个字节才能读取为一个long
        if (byteBuf.readableBytes()>=8)
            list.add(byteBuf.readLong());
    }
}
