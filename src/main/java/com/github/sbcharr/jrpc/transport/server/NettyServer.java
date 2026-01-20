package com.github.sbcharr.jrpc.transport.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyServer {

    private final NettyServerConfig config;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private Channel serverChannel;

    public NettyServer(NettyServerConfig config) {
        this.config = config;
    }

    public void start() throws InterruptedException {
        // 1. Create the Thread Pools
        // bossGroup: Accepts incoming connections
        bossGroup = new NioEventLoopGroup(config.getBossThreads());
        // workerGroup: Handles the actual data processing
        workerGroup = new NioEventLoopGroup(config.getWorkerThreads());

        // 2. Configure the Server
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class) // Use NIO (Non-blocking I/O) sockets
                // server socket options
                .option(ChannelOption.SO_BACKLOG, 1024) // Max queue of pending connections
                .option(ChannelOption.SO_REUSEADDR, true)

                // child socket options
                .childOption(ChannelOption.SO_KEEPALIVE, true) // Keep connections alive
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ServerChannelInitializer());

        // 4. Start the Server (Bind to port)
        // .sync() waits for the server to start successfully
        ChannelFuture future = bootstrap.bind(config.getPort()).sync();
        serverChannel = future.channel();

        log.info("Netty server started on port {}", config.getPort());
    }

    public void stop() {
        log.info("Shutting down Netty server...");
        if (serverChannel != null) {
            serverChannel.close();
        }
        if (workerGroup != null) {
            workerGroup.shutdownGracefully();
        }
        if (bossGroup != null) {
            bossGroup.shutdownGracefully();
        }
    }
}


