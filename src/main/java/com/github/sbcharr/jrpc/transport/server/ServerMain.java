package com.github.sbcharr.jrpc.transport.server;


public class ServerMain {
    public static void main(String[] args) {
        RpcServerConfig config = new RpcServerConfig(
                8080,
                1,
                Runtime.getRuntime().availableProcessors()
        );

        RpcServer server = new RpcServer(config);
        Runtime.getRuntime().addShutdownHook(new Thread(server::stop));

        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
            server.stop();
        }
    }
}
