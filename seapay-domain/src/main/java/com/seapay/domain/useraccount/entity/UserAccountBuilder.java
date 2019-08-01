package com.seapay.domain.useraccount.entity;

public final class UserAccountBuilder {
    private Long id;
    private String userID;
    private String name;
    private String email;
    private String phonenumber;
    private int userType;

    private UserAccountBuilder() {
    }

    public static UserAccountBuilder anUserAccount() {
        return new UserAccountBuilder();
    }

    public UserAccountBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public UserAccountBuilder withUserID(String userID) {
        this.userID = userID;
        return this;
    }

    public UserAccountBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UserAccountBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserAccountBuilder withPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
        return this;
    }

    public UserAccountBuilder withUserType(int userType) {
        this.userType = userType;
        return this;
    }

    public UserAccount build() {
        return new UserAccount(id, userID, name, email, phonenumber, userType);
    }
}
