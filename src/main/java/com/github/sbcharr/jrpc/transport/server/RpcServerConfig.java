package com.github.sbcharr.jrpc.transport.server;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class RpcServerConfig {
    private final int port;
    private final int bossThreads;
    private final int workerThreads;
}
