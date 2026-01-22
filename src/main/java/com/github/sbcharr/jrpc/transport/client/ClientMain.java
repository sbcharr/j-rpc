package com.github.sbcharr.jrpc.transport.client;

import com.github.sbcharr.jrpc.codec.DecodedMessage;
import com.github.sbcharr.jrpc.protocol.MessageType;
import com.github.sbcharr.jrpc.protocol.RpcRequest;
import com.github.sbcharr.jrpc.serialize.Serializer;
import com.github.sbcharr.jrpc.serialize.SerializerFactory;
import com.github.sbcharr.jrpc.serialize.SerializerType;
import io.netty.channel.Channel;

public class ClientMain {
    public static void main(String[] args) throws Exception {
        RpcRequest request = new RpcRequest().builder()
                .requestId("1")
                .service("UserService")
                .method("getUser")
                .paramTypes(new String[]{"int"})
                .args(new Object[]{1})
                .build();

        Serializer serializer = SerializerFactory.get(SerializerType.JSON);
        byte[] payload = serializer.serialize(request);
        DecodedMessage message = new DecodedMessage(MessageType.REQUEST, SerializerType.JSON, 1, payload);

        RpcClient client = new RpcClient("127.0.0.1", 8080);
        Channel ch = client.connect();
        ch.writeAndFlush(message);
    }
}
