package com.github.sbcharr.jrpc.transport.server;

import com.github.sbcharr.jrpc.codec.DecodedMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServerInboundHandler extends SimpleChannelInboundHandler<DecodedMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DecodedMessage message) {
        if (message == null) {
            log.warn("Received null message");
            return;
        }
        log.debug("Received message with requestId={}", message.getRequestId());
        ctx.writeAndFlush(message);
    }
}
