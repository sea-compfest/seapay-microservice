package com.seapay.domain.useraccount.entity;

public class UserAccount {
    private Long id;
    private String userID;
    private String name;
    private String email;
    private String phonenumber;
    private int userType;

    public UserAccount(Long id, String userID, String name, String email, String phonenumber, int userType) {
        this.id = id;
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.phonenumber = phonenumber;
        this.userType = userType;
    }

    public Long getId() {
        return id;
    }

    public String getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public int getUserType() {
        return userType;
    }



}
