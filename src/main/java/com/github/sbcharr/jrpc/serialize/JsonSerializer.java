package com.github.sbcharr.jrpc.serialize;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonSerializer implements Serializer {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public byte getCode() {
        return 0;
    }

    @Override
    public byte[] serialize(Object obj) throws RuntimeException {
        try {
            return MAPPER.writeValueAsBytes(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Json serialization failed", e);
        }
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> clazz) throws Exception {
        try {
            return MAPPER.readValue(data, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Json deserialization failed", e);
        }
    }
}
