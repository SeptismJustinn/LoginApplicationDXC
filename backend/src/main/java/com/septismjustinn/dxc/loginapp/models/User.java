package com.septismjustinn.dxc.loginapp.models;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "HASH")
    private String hash;

    @Column(name = "ROLE")
    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {
        this.id = UUID.randomUUID();
    }

    public User(String name, String username, String hash, Role role) {
        this.name = name;
        this.username = username;
        this.hash = hash;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getHash() {
        return hash;
    }

    public Role getRole() {
        return role;
    }
}
