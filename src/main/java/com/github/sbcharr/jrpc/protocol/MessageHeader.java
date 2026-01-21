package com.github.sbcharr.jrpc.protocol;

import lombok.Data;

@Data
public class MessageHeader {
    private static final int MAGIC_NUMBER = 0xCAFEBABE; // Unique identifier for protocol JRPC
    private static final int HEADER_SIZE = 16;

    // wire fields
    private int magic = MAGIC_NUMBER; // Magic number -- 4 bytes
    private byte version = 1; // Protocol version -- 1 byte
    private MessageType messageType; // 0 = request, 1 = response -- 1 byte
    private SerializerType serializer; // 0 = JSON, 1 = Protobuf -- 1 byte
    private int payloadLength; // Length of the payload in bytes -- 4 bytes
    private int requestId; // Unique request ID -- 4 bytes
    private byte reserved = 0; // Reserved for future use -- 1 byte
}
