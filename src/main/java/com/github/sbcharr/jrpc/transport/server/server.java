package com.github.sbcharr.jrpc.transport.server;


public class server {
    public static void main(String[] args) throws Exception {
        NettyServerConfig serverConfig = new NettyServerConfig(8080, 1,
                Runtime.getRuntime().availableProcessors());

        NettyServer server = new NettyServer(serverConfig);
        Runtime.getRuntime().addShutdownHook(new Thread(server::stop));

        server.start();
    }
}
