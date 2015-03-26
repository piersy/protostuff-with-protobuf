package com.piersy.prototest;

import com.piersy.prototest.generated.IdMessage;
import com.piersy.prototest.generated.UserAccountsMessage;
import org.junit.Before;
import org.junit.Test;
import org.objenesis.ObjenesisStd;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class UserAccountProtostuffProtobufSerialisationDeserialisationTest {

    public static final String USER_NAME = "userName";
    public static final String ACCOUNT_TYPE = "accountType";
    public static final String PASSWORD = "password";
    private ProtostuffSerialiser protostuffSerialiser;

    @Before
    public void setUp() throws Exception {
        protostuffSerialiser = new ProtostuffSerialiser(new ObjenesisStd(true));

    }

    @Test
    public void givenObject_whenSerialisedWithProtostuff_thenDeserialisationByProtostuffResultsInOriginalObject() throws Exception {
        UserAccount userAccount = new UserAccount(USER_NAME, ACCOUNT_TYPE, PASSWORD);

        assertThat(protostuffSerialiser.deserialise(protostuffSerialiser.serialise(userAccount), UserAccount.class), equalTo(userAccount));
    }

    @Test
    public void givenObject_whenSerialisedWithProtobuf_thenDeserialisationByProtobufResultsInOriginalObject() throws Exception {
        UserAccountsMessage.UserAccounts.UserAccount userAccount = UserAccountsMessage.UserAccounts.UserAccount.newBuilder().setUserName(USER_NAME).setAccountType(
                ACCOUNT_TYPE).setPassword(PASSWORD).build();

        assertThat(UserAccountsMessage.UserAccounts.UserAccount.parseFrom(userAccount.toByteArray()), equalTo(userAccount));
    }

    @Test
    public void givenObject_whenSerialisedWithProtostuff_thenDeserialisationByProtobufResultsInMatchingObject() throws Exception {
        UserAccount userAccount = new UserAccount(USER_NAME, ACCOUNT_TYPE, PASSWORD);

        byte[] bytes = protostuffSerialiser.serialise(userAccount);

        UserAccountsMessage.UserAccounts.UserAccount deserialisedUserAccount = UserAccountsMessage.UserAccounts.UserAccount.parseFrom(bytes);


        assertThat(deserialisedUserAccount.getUserName(), equalTo(userAccount.getUserName()));
        assertThat(deserialisedUserAccount.getAccountType(), equalTo(userAccount.getAccountType()));
        assertThat(deserialisedUserAccount.getPassword(), equalTo(userAccount.getPassword()));
    }


    @Test
    public void givenObject_whenSerialisedWithProtobuf_thenDeserialisationByProtostuffResultsInMatchingObject() throws Exception {
        UserAccountsMessage.UserAccounts.UserAccount userAccount = UserAccountsMessage.UserAccounts.UserAccount.newBuilder().setUserName(USER_NAME).setAccountType(
                ACCOUNT_TYPE).setPassword(PASSWORD).build();

        UserAccount deserialisedUserAccount = protostuffSerialiser.deserialise(userAccount.toByteArray(), UserAccount.class);

        assertThat(deserialisedUserAccount.getUserName(), equalTo(userAccount.getUserName()));
        assertThat(deserialisedUserAccount.getAccountType(), equalTo(userAccount.getAccountType()));
        assertThat(deserialisedUserAccount.getPassword(), equalTo(userAccount.getPassword()));
    }

}
