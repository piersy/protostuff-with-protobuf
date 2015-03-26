package com.piersy.prototest;

public class UserAccount {
    private final String userName;
    private final String accountType;
    private final String password;

    public UserAccount(String userName, String accountType, String password) {
        this.userName = userName;
        this.accountType = accountType;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAccount that = (UserAccount) o;

        if (!accountType.equals(that.accountType)) return false;
        if (!password.equals(that.password)) return false;
        if (!userName.equals(that.userName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userName.hashCode();
        result = 31 * result + accountType.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }
}
