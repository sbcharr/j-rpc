package com.github.sbcharr.jrpc.transport.server;

import lombok.Getter;

@Getter
public final class NettyServerConfig {
    private final int port;
    private final int bossThreads;
    private final int workerThreads;

    public NettyServerConfig(int port, int bossThreads, int workerThreads) {
        this.port = port;
        this.bossThreads = bossThreads;
        this.workerThreads = workerThreads;
    }
}
