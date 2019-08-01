package com.seapay.api.useraccount.request;

import com.google.gson.annotations.SerializedName;

public class CreateUserAccountRequest {
    @SerializedName("user_id")
    private String userID;
    private String name;
    private String email;
    private String phonenumber;
    @SerializedName("user_type")
    private int userType;

    public CreateUserAccountRequest(String userID, String name, String email, String phonenumber, int userType) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.phonenumber = phonenumber;
        this.userType = userType;
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


    public static final class CreateUserAccountRequestBuilder {
        private String userID;
        private String name;
        private String email;
        private String phonenumber;
        private int userType;

        private CreateUserAccountRequestBuilder() {
        }

        public static CreateUserAccountRequestBuilder aCreateUserAccountRequest() {
            return new CreateUserAccountRequestBuilder();
        }

        public CreateUserAccountRequestBuilder withUserID(String userID) {
            this.userID = userID;
            return this;
        }

        public CreateUserAccountRequestBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public CreateUserAccountRequestBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public CreateUserAccountRequestBuilder withPhonenumber(String phonenumber) {
            this.phonenumber = phonenumber;
            return this;
        }

        public CreateUserAccountRequestBuilder withUserType(int userType) {
            this.userType = userType;
            return this;
        }

        public CreateUserAccountRequest build() {
            return new CreateUserAccountRequest(userID, name, email, phonenumber, userType);
        }
    }
}
