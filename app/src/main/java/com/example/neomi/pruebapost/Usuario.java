package com.example.neomi.pruebapost;
import com.google.firebase.database.IgnoreExtraProperties;
@IgnoreExtraProperties
public class Usuario {
    private String userId;
    private String userName;
    private String userLastName;
    private String userEmail;

    public Usuario() {
    }

    public Usuario(String userId, String userName, String userLastName, String userEmail) {
        this.userId = userId;
        this.userName = userName;
        this.userLastName = userLastName;
        this.userEmail = userEmail;
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

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
