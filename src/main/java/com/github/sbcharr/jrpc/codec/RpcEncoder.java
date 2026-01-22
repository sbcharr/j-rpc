package com.github.sbcharr.jrpc.codec;

import com.github.sbcharr.jrpc.protocol.MessageHeader;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class RpcEncoder extends MessageToByteEncoder<DecodedMessage> {
    @Override
    protected void encode(ChannelHandlerContext ctx, DecodedMessage message, ByteBuf out) {
        out.writeInt(MessageHeader.MAGIC_NUMBER);
        out.writeByte(MessageHeader.VERSION);
        out.writeByte(message.getMessageType());
        out.writeByte(message.getSerializerType());
        out.writeInt(message.getBody().length);
        out.writeInt(message.getRequestId());
        out.writeByte(0); // reserved byte

        out.writeBytes(message.getBody()); // payload
    }
}
