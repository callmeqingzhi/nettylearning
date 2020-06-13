package com.cz.netty.codec2;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class NettyServerHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("has exception:" + cause.getMessage());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MyDataInfo.MyMessage myMessage)
            throws Exception {
        if (myMessage.getDataType() == MyDataInfo.MyMessage.DataType.studentType) {
            MyDataInfo.Student student = myMessage.getStudent();
            System.out.println("student id=" + student.getId() + " name=" + student.getName());
        } else if (myMessage.getDataType() == MyDataInfo.MyMessage.DataType.workerType) {
            MyDataInfo.Worker worker = myMessage.getWorker();
            System.out.println("worker id=" + worker.getId() + " name=" + worker.getName());
        } else {
            System.out.println("error type");
        }
    }
}
