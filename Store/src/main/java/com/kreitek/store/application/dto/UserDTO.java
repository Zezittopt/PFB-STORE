package com.kreitek.store.application.dto;

import java.io.Serializable;
import java.util.Set;

public class UserDTO implements Serializable {
    private Set<ItemDTO> favorites;
    private Long id;
    private String userName;
    private String name;
    private String surNames;
    private String phone;
    private String email;
    private String password;
    private String rol;

    public UserDTO() {    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurNames() {
        return surNames;
    }

    public void setSurNames(String surNames) {
        this.surNames = surNames;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Set<ItemDTO> getFavorites() {
        return favorites;
    }

    public void setFavorites(Set<ItemDTO> favorites) {
        this.favorites = favorites;
    }
}
