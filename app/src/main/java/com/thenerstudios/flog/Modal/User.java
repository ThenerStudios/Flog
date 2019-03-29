package com.thenerstudios.flog.Modal;

public class User {

    private int id;
    private String fullname, email, address, phone;

    public User(int id, String fullname, String email, String address, String phone) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }
}
