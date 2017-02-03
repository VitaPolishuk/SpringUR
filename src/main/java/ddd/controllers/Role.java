package ddd.controllers;

import java.io.Serializable;

public class Role implements Serializable{
    private String id_roles;
    private String name_roles;
    public Role() {
    }
    public Role(String id_roles, String name_roles){
        this.id_roles = id_roles;
        this.name_roles = name_roles;

    }

    public String getId_roles() {
        return id_roles;
    }

    public String getName_roles() {
        return name_roles;
    }

    public void setId_roles(String id_roles) {
        this.id_roles = id_roles;
    }

    public void setName_roles(String name_roles) {
        this.name_roles = name_roles;
    }
}
