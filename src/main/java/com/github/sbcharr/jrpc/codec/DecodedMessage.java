package com.github.sbcharr.jrpc.codec;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DecodedMessage {
    private byte messageType;
    private byte serializerType;
    private int requestId;
    private byte[] body;
}
