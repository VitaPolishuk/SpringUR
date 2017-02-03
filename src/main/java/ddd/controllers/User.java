package ddd.controllers;

import java.io.Serializable;

public class User implements Serializable{
    private String id_user;
    private String name_user;
    private String email;
    private String id_roles;
    public User() {
    }

    public User(String id_user, String name_user, String email, String id_roles) {
        this.id_user = id_user;
        this.name_user = name_user;
        this.email = email;
        this.id_roles = id_roles;
    }


    public String getEmail() {
        return email;
    }

    public String getId_user() {
        return id_user;
    }

    public String getId_roles() {
        return id_roles;
    }

    public String getName_user() {
        return name_user;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public void setId_roles(String id_roles) {
        this.id_roles = id_roles;
    }

    public void setName_user(String name_user) {
        this.name_user = name_user;
    }
}
