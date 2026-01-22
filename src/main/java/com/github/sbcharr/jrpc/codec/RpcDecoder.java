package com.github.sbcharr.jrpc.codec;

import com.github.sbcharr.jrpc.protocol.MessageHeader;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class RpcDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws IllegalArgumentException {
        if (in.readableBytes() < MessageHeader.HEADER_SIZE) {
            return; // Not enough data to read the header
        }

        in.markReaderIndex(); // Mark the current read position

        int magic = in.readInt();
        if (magic != MessageHeader.MAGIC_NUMBER) {
            ctx.close();
            throw new IllegalArgumentException("Invalid magic number: " + magic);
        }

        byte version = in.readByte();
        byte messageType = in.readByte();
        byte serializationType = in.readByte();
        int payloadLength = in.readInt();
        int requestId = in.readInt();
        byte reserved = in.readByte(); // placeholder for future use

        if (in.readableBytes() < payloadLength) {
            in.resetReaderIndex(); // Not enough data, reset to marked position
            return;
        }

        byte[] payload = new byte[payloadLength];
        in.readBytes(payload);

        out.add(new DecodedMessage(messageType, serializationType, requestId, payload));
    }
}
