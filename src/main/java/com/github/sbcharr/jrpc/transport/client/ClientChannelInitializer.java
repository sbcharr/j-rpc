package com.github.sbcharr.jrpc.transport.client;

import com.github.sbcharr.jrpc.codec.RpcDecoder;
import com.github.sbcharr.jrpc.codec.RpcEncoder;
import com.github.sbcharr.jrpc.transport.handler.RpcClientHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;

public final class ClientChannelInitializer extends ChannelInitializer<Channel> {

    @Override
    protected void initChannel(Channel channel) {
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast(new RpcDecoder());
        pipeline.addLast(new RpcEncoder());
        pipeline.addLast(new RpcClientHandler());
    }
}
