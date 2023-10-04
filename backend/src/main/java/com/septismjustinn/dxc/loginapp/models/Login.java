package com.septismjustinn.dxc.loginapp.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "logins")
public class Login {
    @Id
    private UUID jti;

//    @Column(name = "REFRESH")
//    private boolean refresh;
//
//    @Column(name = "ACCESS_PARENT")
//    private UUID access_parent;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User user;

    public Login() {
    }

    public Login(UUID jti, User user) {
        this.jti = jti;
        this.user = user;
    }

    //    public Login(UUID jti, boolean refresh, UUID access_parent, UUID user) {
//        this.jti = jti;
//        this.refresh = refresh;
//        this.access_parent = access_parent;
//        this.user = user;
//    }


    public UUID getJti() {
        return jti;
    }

    public User getUser() {
        return user;
    }
}
