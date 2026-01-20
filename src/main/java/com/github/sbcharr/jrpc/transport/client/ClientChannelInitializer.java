package com.github.sbcharr.jrpc.transport.client;

import com.github.sbcharr.jrpc.transport.handler.ClientInboundHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public final class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) {

        channel.pipeline()
                .addLast(new StringEncoder())
                .addLast(new StringDecoder())
                .addLast(new ClientInboundHandler());
    }
}
