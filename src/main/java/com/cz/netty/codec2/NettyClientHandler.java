package com.cz.netty.codec2;

import com.cz.netty.codec.StudentPOJO;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Random;

public class NettyClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        int i = new Random().nextInt(3);
        MyDataInfo.MyMessage dataInfo = null;
        if (i == 0) {
            dataInfo = MyDataInfo.MyMessage.newBuilder()
                    .setDataType(MyDataInfo.MyMessage.DataType.studentType)
                    .setStudent(MyDataInfo.Student.newBuilder().setId(0).setName("Jack").build())
                    .build();
        } else {
            dataInfo = MyDataInfo.MyMessage.newBuilder()
                    .setDataType(MyDataInfo.MyMessage.DataType.workerType)
                    .setWorker(MyDataInfo.Worker.newBuilder().setId(0).setName("OldJack").build())
                    .build();
        }
        ctx.writeAndFlush(dataInfo);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println(msg.toString());
    }
}
