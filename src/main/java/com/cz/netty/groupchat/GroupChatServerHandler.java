package com.cz.netty.groupchat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @Description TODO
 * @Author cz
 * @Date 2020/6/11 15:21
 **/
public class GroupChatServerHandler extends SimpleChannelInboundHandler<String> {
    // 定义一个channel组，管理所有channel
    // GlobalEventExecutor.INSTANCE 全局事件执行器，单例
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * * 一旦建立连接，第一个被触发
     * *
     * * @param ctx     *
     *
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        // 将该客户端加入的信息发送给其他所有的channel，不需要我们自己手动遍历发送
        channelGroup.writeAndFlush("用户：" + channel.remoteAddress() + " 加入了群聊\n");
        channelGroup.add(channel);
    }

    /**
     * 断开连接
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("用户：" + channel.remoteAddress() + " 离开了群聊\n");
        System.out.println("当前用户数：" + channelGroup.size());
    }

    /**
     * 表示channel处于活动状态，提示xxx上线了
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("用户：" + channel.remoteAddress() + " 上线了\n");
    }

    /**
     * 表示channel处于非活动状态，提示xxx离线了
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("用户：" + channel.remoteAddress() + " 离线了\n");
    }

    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.forEach(ch -> {
            if (ch != channel) {
                ch.writeAndFlush("用户 " + channel.remoteAddress() + " 说：" + msg + "\n");
            } else {// 当前channel是自己
                ch.writeAndFlush("我发送了：" + msg + "\n");
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("发生异常：" + cause.getMessage());
        ctx.close();
    }
}