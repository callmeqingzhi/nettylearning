package com.cz.netty.heartbeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @Description TODO
 * @Author cz
 * @Date 2020/6/12 10:08
 **/
public class MyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        IdleStateEvent event = (IdleStateEvent) evt;
        String eventType = null;
        switch (event.state()) {
            case ALL_IDLE:
                eventType = "ALL_IDLE";
                break;
            case READER_IDLE:
                eventType = "READER_IDLE";
                break;
            case WRITER_IDLE:
                eventType = "WRITER_IDLE";
                break;
        }
        System.out.println(ctx.channel().remoteAddress() + "--time out--" + eventType);
        System.out.println("server deal...");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("client: " + ctx.channel().remoteAddress() + " Exception:" + cause.getMessage());
    }
}