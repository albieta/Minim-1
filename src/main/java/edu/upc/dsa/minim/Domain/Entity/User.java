package edu.upc.dsa.minim.Domain.Entity;

public class User {
    String userId;
    String userName;
    String userSurname;

    public User(String userId, String userName, String userSurname){
        this.userId = userId;
        this.userName = userName;
        this.userSurname = userSurname;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
