package com.github.sbcharr.jrpc.transport.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RpcServer {

    private final RpcServerConfig config;

    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private Channel channel;

    public RpcServer(RpcServerConfig config) {
        this.config = config;
    }

    public void start() throws InterruptedException {

        bossGroup = new NioEventLoopGroup(config.getBossThreads());
        workerGroup = new NioEventLoopGroup(config.getWorkerThreads());

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class) // Use NIO (Non-blocking I/O) sockets
                // ServerMain socket options
                .option(ChannelOption.SO_BACKLOG, 1024) // Max queue of pending connections
                .option(ChannelOption.SO_REUSEADDR, true)

                // child socket options
                .childOption(ChannelOption.SO_KEEPALIVE, true) // Keep connections alive
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ServerChannelInitializer());

        bootstrap.bind(config.getPort()).sync().channel();
        log.info(
                "RPC server started on port {} (bossThreads={}, workerThreads={})",
                config.getPort(),
                config.getBossThreads(),
                config.getWorkerThreads()
        );
    }

    public void stop() {
        log.info("Shutting down Rpc server...");
        if (channel != null) {
            channel.close();
        }
        if (workerGroup != null) {
            workerGroup.shutdownGracefully();
        }
        if (bossGroup != null) {
            bossGroup.shutdownGracefully();
        }
    }
}


