package com.piersy.prototest;

import com.piersy.prototest.generated.UserAccountsMessage;
import org.junit.Before;
import org.junit.Test;
import org.objenesis.ObjenesisStd;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class UserAccountsProtostuffProtobufSerialisationDeserialisationTest {

    public static final String USER_NAME = "userName";
    public static final String ACCOUNT_TYPE = "accountType";
    public static final String PASSWORD = "password";
    private ProtostuffSerialiser protostuffSerialiser;
    private UserAccounts userAccounts;
    private UserAccountsMessage.UserAccounts userAccountsMessage;

    @Before
    public void setUp() throws Exception {
        protostuffSerialiser = new ProtostuffSerialiser(new ObjenesisStd(true));
        userAccounts = new UserAccounts(Arrays.asList(new UserAccount(USER_NAME, ACCOUNT_TYPE, PASSWORD)));
        userAccountsMessage = UserAccountsMessage.UserAccounts.newBuilder().addUserAccounts(
                UserAccountsMessage.UserAccounts.UserAccount.newBuilder().setUserName(USER_NAME).setAccountType(ACCOUNT_TYPE).setPassword(PASSWORD)).build();
    }

    @Test
    public void givenObject_whenSerialisedWithProtostuff_thenDeserialisationByProtostuffResultsInOriginalObject() throws Exception {
        byte[] serialise = protostuffSerialiser.serialise(userAccounts);

        assertThat(protostuffSerialiser.deserialise(serialise, UserAccounts.class), equalTo(userAccounts));
    }

    @Test
    public void givenObject_whenSerialisedWithProtobuf_thenDeserialisationByProtobufResultsInOriginalObject() throws Exception {
        assertThat(UserAccountsMessage.UserAccounts.parseFrom(userAccountsMessage.toByteArray()), equalTo(userAccountsMessage));
    }

    @Test
    public void givenObject_whenSerialisedWithProtostuff_thenDeserialisationByProtobufResultsInMatchingObject() throws Exception {

        byte[] bytes = protostuffSerialiser.serialise(userAccounts);

        UserAccountsMessage.UserAccounts deserialisedUserAccounts = UserAccountsMessage.UserAccounts.parseFrom(bytes);


        assertThat(deserialisedUserAccounts.getUserAccountsList().get(0).getUserName(), equalTo(USER_NAME));
        assertThat(deserialisedUserAccounts.getUserAccountsList().get(0).getAccountType(), equalTo(ACCOUNT_TYPE));
        assertThat(deserialisedUserAccounts.getUserAccountsList().get(0).getPassword(), equalTo(PASSWORD));
    }

    @Test
    public void givenObject_whenSerialisedWithProtobuf_thenDeserialisationByProtostuffResultsInMatchingObject() throws Exception {
        UserAccounts deserialisedUserAccounts = protostuffSerialiser.deserialise(userAccountsMessage.toByteArray(), UserAccounts.class);

        assertThat(deserialisedUserAccounts.getUserAccounts().get(0).getUserName(), equalTo(USER_NAME));
        assertThat(deserialisedUserAccounts.getUserAccounts().get(0).getAccountType(), equalTo(ACCOUNT_TYPE));
        assertThat(deserialisedUserAccounts.getUserAccounts().get(0).getPassword(), equalTo(PASSWORD));
    }

}
