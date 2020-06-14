package com.cz.netty.protocoltcp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;


public class MyClientHandler extends SimpleChannelInboundHandler<MessageProtocol> {

    private int count;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 使用客户端发送十条消息"hello,world!"
        for (int i = 0; i < 5; i++) {
            byte[] bytes = "hello,world!".getBytes(CharsetUtil.UTF_8);
            MessageProtocol msg = new MessageProtocol();
            msg.setContent(bytes);
            msg.setLen(bytes.length);
            ctx.writeAndFlush(msg);
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol messageProtocol) throws Exception {
//        byte[] bytes = new byte[byteBuf.readableBytes()];
//        byteBuf.readBytes(bytes);
//        String msg = new String(bytes, CharsetUtil.UTF_8);
//        System.out.println("client receive: " + msg);
//        System.out.println("client receive count = " + (++this.count));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(cause.getMessage());
        ctx.close();
    }
}
