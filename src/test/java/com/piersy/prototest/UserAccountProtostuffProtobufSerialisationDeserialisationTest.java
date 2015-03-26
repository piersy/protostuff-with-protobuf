package com.piersy.prototest;

import com.piersy.prototest.generated.IdMessage;
import com.piersy.prototest.generated.UserAccountsMessage;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.objenesis.ObjenesisStd;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class UserAccountProtostuffProtobufSerialisationDeserialisationTest {

    public static final String USER_NAME = "userName";
    public static final String ACCOUNT_TYPE = "accountType";
    public static final String PASSWORD = "password";
    private ProtostuffSerialiser protostuffSerialiser;
    private UserAccount userAccount;
    private UserAccountsMessage.UserAccounts.UserAccount userAccountMessage;

    @Before
    public void setUp() throws Exception {
        userAccount = new UserAccount(USER_NAME, ACCOUNT_TYPE, PASSWORD);
        userAccountMessage = UserAccountsMessage.UserAccounts.UserAccount.newBuilder().setUserName(USER_NAME).setAccountType(
                ACCOUNT_TYPE).setPassword(PASSWORD).build();

        protostuffSerialiser = new ProtostuffSerialiser(new ObjenesisStd(true));
    }

    @Ignore
    @Test
    public void writeSerialisedMessagesToFiles() throws Exception {
        Files.write(Paths.get("proto-messages/UserAccountMessage.protostuff"), protostuffSerialiser.serialise(userAccount));
        Files.write(Paths.get("proto-messages/UserAccountMessage.protobuf"), userAccountMessage.toByteArray());
    }


    @Test
    public void givenObject_whenSerialisedWithProtostuff_thenDeserialisationByProtostuffResultsInOriginalObject() throws Exception {
        assertThat(protostuffSerialiser.deserialise(protostuffSerialiser.serialise(userAccount), UserAccount.class), equalTo(userAccount));
    }

    @Test
    public void givenObject_whenSerialisedWithProtobuf_thenDeserialisationByProtobufResultsInOriginalObject() throws Exception {
        assertThat(UserAccountsMessage.UserAccounts.UserAccount.parseFrom(userAccountMessage.toByteArray()), equalTo(userAccountMessage));
    }

    @Test
    public void givenObject_whenSerialisedWithProtostuff_thenDeserialisationByProtobufResultsInMatchingObject() throws Exception {
        UserAccountsMessage.UserAccounts.UserAccount deserialisedUserAccount = UserAccountsMessage.UserAccounts.UserAccount.parseFrom(protostuffSerialiser.serialise(userAccount));


        assertThat(deserialisedUserAccount.getUserName(), equalTo(userAccount.getUserName()));
        assertThat(deserialisedUserAccount.getAccountType(), equalTo(userAccount.getAccountType()));
        assertThat(deserialisedUserAccount.getPassword(), equalTo(userAccount.getPassword()));
    }


    @Test
    public void givenObject_whenSerialisedWithProtobuf_thenDeserialisationByProtostuffResultsInMatchingObject() throws Exception {
        UserAccount deserialisedUserAccount = protostuffSerialiser.deserialise(userAccountMessage.toByteArray(), UserAccount.class);

        assertThat(deserialisedUserAccount.getUserName(), equalTo(userAccount.getUserName()));
        assertThat(deserialisedUserAccount.getAccountType(), equalTo(userAccount.getAccountType()));
        assertThat(deserialisedUserAccount.getPassword(), equalTo(userAccount.getPassword()));
    }

}
