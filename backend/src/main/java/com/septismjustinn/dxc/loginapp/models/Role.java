package com.septismjustinn.dxc.loginapp.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @Column(name = "ROLE_NAME")
    private String rolename;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private List<User> usersList;

    public Role() {
    }

    public Role(String rolename, List<User> usersList) {
        this.rolename = rolename;
        this.usersList = usersList;
    }

    public String getRolename() {
        return rolename;
    }
}
