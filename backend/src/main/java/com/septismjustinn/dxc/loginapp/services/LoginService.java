package com.septismjustinn.dxc.loginapp.services;

import com.septismjustinn.dxc.loginapp.data.LoginRepository;
import com.septismjustinn.dxc.loginapp.data.UserRepository;
import com.septismjustinn.dxc.loginapp.models.Login;
import com.septismjustinn.dxc.loginapp.models.User;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LoginService {
    private final UserRepository userRepo;
    private final LoginRepository loginRepo;
    private final AuthenticationManager authenticationManager;

    public LoginService(UserRepository userRepo, LoginRepository loginRepo, AuthenticationManager authenticationManager) {
        this.userRepo = userRepo;
        this.loginRepo = loginRepo;
        this.authenticationManager = authenticationManager;
    }

    public User login(String username, String password) throws BadCredentialsException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        User user = userRepo.findByUsername(username)
                .orElseThrow(()->new BadCredentialsException("Invalid username/password"));
        return user;
    }

    @Transactional
    public boolean registerLogin(UUID jti, User user) {
        try {
            // Remove all previous user logins
            loginRepo.deleteByUser(user);
            loginRepo.flush();
            loginRepo.save(new Login(jti, user));
            return true;
        } catch (Exception e) {
            // In case of errors in database
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Transactional
    public boolean registerLogout(UUID jti) {
        try {
            loginRepo.deleteById(jti);
            loginRepo.flush();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
