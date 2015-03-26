package com.piersy.prototest;

import java.util.List;

public class UserAccounts {
    private final List<UserAccount> userAccounts;

    public UserAccounts(List<UserAccount> userAccounts) {
        this.userAccounts = userAccounts;
    }

    public List<UserAccount> getUserAccounts() {
        return userAccounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAccounts that = (UserAccounts) o;

        if (!userAccounts.equals(that.userAccounts)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return userAccounts.hashCode();
    }
}

