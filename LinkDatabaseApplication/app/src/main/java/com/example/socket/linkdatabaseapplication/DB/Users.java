package com.example.socket.linkdatabaseapplication.DB;

public class Users {
    private String name;
    private String  PermissionList;
    public String  getNames() {
        return name;
    }

    public void setNames(String  name) {
        this.name = name;
    }

    public String  getPermissionLists() {
        return  PermissionList;
    }

    public void setPermissionLists(String   PermissionList) {
        this. PermissionList =  PermissionList;
    }
    public Users(String  name, String  PermissionList) {

        this.name = name;
        this.PermissionList = PermissionList;
    }

    public Users() {

    }
}
