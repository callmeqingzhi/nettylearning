package com.cz.netty.protocoltcp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;


public class MyServerHandler extends SimpleChannelInboundHandler<MessageProtocol> {

    private int count;

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(cause.getMessage());
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol messageProtocol) throws Exception {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("server receive msg...");
        System.out.println("length: " + messageProtocol.getLen());
        System.out.println("content: " + new String(messageProtocol.getContent(), CharsetUtil.UTF_8));
        System.out.println("count: " + (++this.count));
    }
}
