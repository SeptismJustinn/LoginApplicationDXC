package com.septismjustinn.dxc.loginapp.services;

import com.septismjustinn.dxc.loginapp.data.LoginRepository;
import com.septismjustinn.dxc.loginapp.data.UserRepository;
import com.septismjustinn.dxc.loginapp.models.Login;
import com.septismjustinn.dxc.loginapp.models.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class LoginService {
    private final UserRepository userRepo;
    private final LoginRepository loginRepo;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    public LoginService(UserRepository userRepo, LoginRepository loginRepo, JWTService jwtService, AuthenticationManager authenticationManager) {
        this.userRepo = userRepo;
        this.loginRepo = loginRepo;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public String login(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        User user = userRepo.findByUsername(username)
                .orElseThrow(()->new IllegalArgumentException("Invalid username/password"));
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name", user.getName());
        extraClaims.put("role", user.getRole());
        UUID jti = UUID.randomUUID();
        extraClaims.put("jti", jti);
        loginRepo.save(new Login(jti, false, null, user));
        return jwtService.generateAccessToken(extraClaims, user);
    }
}
