package com.septismjustinn.dxc.loginapp.services;

import com.septismjustinn.dxc.loginapp.data.UserRepository;
import com.septismjustinn.dxc.loginapp.models.Role;
import com.septismjustinn.dxc.loginapp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    // Username has to be unique
    public Optional<User> getUser(String username) {
        return userRepo.findByUsername(username);
    }

    public List<User> getAllUsers() {
        return new ArrayList<User>(userRepo.findAll());
    }

    // Only to be run after verification
    public User createUser(String name, String username, String password) throws Exception {
        Optional<User> userCheck = getUser(username);
        if (userCheck.isPresent()) {
            throw new Exception("User already exists");
        } else {
            User newUser = new User(name, username, passwordEncoder.encode(password), Role.USER);
            userRepo.save(newUser);
            return newUser;
        }
    }
}
