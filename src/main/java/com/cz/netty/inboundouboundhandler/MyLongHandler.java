package com.cz.netty.inboundouboundhandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyLongHandler extends SimpleChannelInboundHandler<Long> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Long aLong) throws Exception {
        System.out.println("MyLongHandler receive long from client: " + ctx.channel().remoteAddress() + " data is: " + aLong);
        ctx.writeAndFlush(9876L);
    }
}
