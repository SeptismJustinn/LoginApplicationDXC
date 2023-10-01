package com.septismjustinn.dxc.loginapp.services;

import com.septismjustinn.dxc.loginapp.data.LoginRepository;
import com.septismjustinn.dxc.loginapp.data.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final UserRepository userRepo;
    private final LoginRepository loginRepo;

    public LoginService(UserRepository userRepo, LoginRepository loginRepo) {
        this.userRepo = userRepo;
        this.loginRepo = loginRepo;
    }
}
