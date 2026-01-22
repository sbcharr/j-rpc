package com.github.sbcharr.jrpc.serialize;

public interface Serializer {
    byte getCode();

    byte[] serialize(Object obj) throws Exception;

    <T> T deserialize(byte[] data, Class<T> clazz) throws Exception;
}
