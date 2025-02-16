package com.example.demo;
public class UserPojo {

    private String password;

    private String email;
    private String name;

    public UserPojo(String password, String email, String name) {
        this.password = password;
        this.email = email;
        this.name= name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.password = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
