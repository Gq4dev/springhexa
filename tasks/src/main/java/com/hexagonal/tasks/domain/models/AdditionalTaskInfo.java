package com.hexagonal.tasks.domain.models;

public class AdditionalTaskInfo {
    private final Long userId;
    private final String userName;
    private final String userEmail;
    private final String userPhone;


    public AdditionalTaskInfo(Long userId, String userName, String userEmail, String userPhone) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

}
