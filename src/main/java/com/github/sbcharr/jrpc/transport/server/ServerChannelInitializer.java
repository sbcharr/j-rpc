package com.github.sbcharr.jrpc.transport.server;

import com.github.sbcharr.jrpc.codec.RpcDecoder;
import com.github.sbcharr.jrpc.codec.RpcEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public final class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        channel.pipeline()
                .addLast("decoder", new RpcDecoder())
                .addLast("encoder", new RpcEncoder())
                .addLast("handler", new ServerInboundHandler());
    }
}
