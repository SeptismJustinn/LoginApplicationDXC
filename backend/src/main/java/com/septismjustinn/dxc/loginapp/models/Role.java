package com.septismjustinn.dxc.loginapp.models;

public enum Role {
    USER, ADMIN;

    @Override
    public String toString() {
        switch(this) {
            case USER:
                return "User";
            case ADMIN:
                return "Admin";
        };
        return "";
    }
}
