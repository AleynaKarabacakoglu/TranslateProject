package com.example.translateproject;

public class User {
    private String email;
    private String username;
    private String password;
    public User(){}
    public User(String name,String mail,String password)
    {
        this.setEmail(mail);
        this.setPassword(password);
        this.setUsername(name);

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
