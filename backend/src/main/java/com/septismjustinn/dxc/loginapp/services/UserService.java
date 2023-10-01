package com.septismjustinn.dxc.loginapp.services;

import com.septismjustinn.dxc.loginapp.data.UserRepository;
import com.septismjustinn.dxc.loginapp.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    // Username has to be unique
    public Optional<User> getUser(String username) {
        return userRepo.findByUsername(username);
    }
}
