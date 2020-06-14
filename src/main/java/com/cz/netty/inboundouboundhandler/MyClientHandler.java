package com.cz.netty.inboundouboundhandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyClientHandler extends SimpleChannelInboundHandler<Long> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Long aLong) throws Exception {
        System.out.println("MyClientHandler client receive msg from server: " + ctx.channel().remoteAddress() + " data = " + aLong);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("MyClientHandler client write data...");
        ctx.writeAndFlush(123456L);
        // MyLong2ByteEncoder不会处理这种类型的数据 其父类MessageToByteEncoder有如下方法来判断
        /*

        if (this.acceptOutboundMessage(msg)) { // 判断数据类型，如果符合就处理，否则传递给下一个handler
                I cast = msg;
                buf = this.allocateBuffer(ctx, msg, this.preferDirect);

                try {
                    this.encode(ctx, cast, buf);
                } finally {
                    ReferenceCountUtil.release(msg);
                }

                if (buf.isReadable()) {
                    ctx.write(buf, promise);
                } else {
                    buf.release();
                    ctx.write(Unpooled.EMPTY_BUFFER, promise);
                }

                buf = null;
            } else {
                ctx.write(msg, promise);
            }
         */
        //ctx.writeAndFlush(Unpooled.copiedBuffer("abcdadcdabcdabcd", CharsetUtil.UTF_8));
    }
}
