package com.piersy.prototest;

import com.piersy.prototest.generated.IdMessage;
import org.junit.Before;
import org.junit.Test;
import org.objenesis.ObjenesisStd;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class IdProtostuffProtobufSerialisationDeserialisationTest {

    public static final int ID_VALUE = 4;
    private ProtostuffSerialiser protostuffSerialiser;

    @Before
    public void setUp() throws Exception {
        protostuffSerialiser = new ProtostuffSerialiser(new ObjenesisStd(true));

    }

    @Test
    public void givenObject_whenSerialisedWithProtostuff_thenDeserialisationByProtostuffResultsInOriginalObject() throws Exception {
        Id id = new Id(ID_VALUE);

        assertThat(protostuffSerialiser.deserialise(protostuffSerialiser.serialise(id), Id.class), equalTo(id));
    }

    @Test
    public void givenObject_whenSerialisedWithProtobuf_thenDeserialisationByProtobufResultsInOriginalObject() throws Exception {
        IdMessage.Id id = IdMessage.Id.newBuilder().setId(ID_VALUE).build();

        assertThat(IdMessage.Id.parseFrom(id.toByteArray()), equalTo(id));
    }

    @Test
    public void givenObject_whenSerialisedWithProtostuff_thenDeserialisationByProtobufResultsInMatchingObject() throws Exception {
        Id id = new Id(ID_VALUE);

        byte[] bytes = protostuffSerialiser.serialise(id);

        assertThat(IdMessage.Id.parseFrom(bytes).getId(), equalTo(id.getId()));
    }

    @Test
    public void givenObject_whenSerialisedWithProtobuf_thenDeserialisationByProtostuffResultsInMatchingObject() throws Exception {
        IdMessage.Id id = IdMessage.Id.newBuilder().setId(ID_VALUE).build();

        byte[] bytes = id.toByteArray();

        assertThat(protostuffSerialiser.deserialise(bytes, Id.class).getId(), equalTo(id.getId()));
    }

}
