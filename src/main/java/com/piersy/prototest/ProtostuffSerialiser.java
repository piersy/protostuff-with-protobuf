package com.piersy.prototest;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.objenesis.Objenesis;

import java.io.IOException;
import java.io.OutputStream;

public class ProtostuffSerialiser {

    private final Objenesis objenesis;

    public ProtostuffSerialiser(Objenesis objenesis) {
        this.objenesis = objenesis;
    }


    public <T> byte[] serialise(T source) {
        @SuppressWarnings("unchecked")
        Class<T> clazz = (Class<T>) source.getClass();
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            Schema<T> schema = RuntimeSchema.createFrom(clazz);
            return ProtobufIOUtil.toByteArray(source, schema, buffer);
        } finally {
            buffer.clear();
        }
    }

    public <T> T deserialise(byte[] bytes, Class<T> clazz) {
        @SuppressWarnings("unchecked")
        T result = (T) objenesis.newInstance(clazz);
        ProtobufIOUtil.mergeFrom(bytes, result, RuntimeSchema.createFrom(clazz));
        return result;
    }
}
