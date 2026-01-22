package com.github.sbcharr.jrpc.serialize;

import java.util.Map;

public class SerializerFactory {
    private static final Map<Byte, Serializer> SERIALIZERS =
            Map.of(
                    (byte) 0, new JsonSerializer()
            );

    public static Serializer get(byte code) {
        Serializer serializer = SERIALIZERS.get(code);
        if (serializer == null) {
            throw new IllegalArgumentException("Unsupported serializer code: " + code);
        }
        return serializer;
    }

}
