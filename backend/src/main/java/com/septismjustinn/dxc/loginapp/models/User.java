package com.septismjustinn.dxc.loginapp.models;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "USERNAME")
    private String username;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ROLE_NAME")
    private Role role;

    public User() {
        super();
    }

    public User(String name, String username, Role role) {
        super();
        this.name = name;
        this.username = username;
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public Role getRole() {
        return role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
