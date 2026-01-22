package com.github.sbcharr.jrpc.transport.client;

import com.github.sbcharr.jrpc.codec.DecodedMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;


public final class RpcClientHandler extends SimpleChannelInboundHandler<DecodedMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DecodedMessage message) throws Exception {
        System.out.println("Received response, requestId=" + message.getRequestId());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
