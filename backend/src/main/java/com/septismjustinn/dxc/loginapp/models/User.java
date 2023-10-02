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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ROLE_NAME")
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
}
