package edu.upc.dsa.minim.Domain.Entity;

public class User {
    String userId;
    String userName;
    String userSurname;

    public User(){}

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }
}
