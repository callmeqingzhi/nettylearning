package com.cz.netty.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.time.LocalDateTime;

/**
 * @Description TextWebSocketFrame 类型，表示一个文本帧(frame)
 * @Author cz
 * @Date 2020/6/12 16:02
 **/
public class MyTextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        System.out.println("server receive: " + msg.text());
        // 回复消息
        ctx.channel().writeAndFlush(new TextWebSocketFrame("server time" + LocalDateTime.now() + " " + msg.text()));
    }

    // 客户端连接后，触发此方法
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerAdded invoke " + ctx.channel().id().asLongText());
        System.out.println("handlerAdded invoke " + ctx.channel().id().asShortText());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerRemoved invoke " + ctx.channel().id().asLongText());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("exceptionCaught invoke " + ctx.channel().id().asLongText() + " exception: " + cause.getMessage());
        ctx.close();
    }
}