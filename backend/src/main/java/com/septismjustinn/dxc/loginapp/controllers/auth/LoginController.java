package com.septismjustinn.dxc.loginapp.controllers.auth;

import com.septismjustinn.dxc.loginapp.models.User;
import com.septismjustinn.dxc.loginapp.services.JWTService;
import com.septismjustinn.dxc.loginapp.services.LoginService;
import com.septismjustinn.dxc.loginapp.validators.AuthRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/public/login")
public class LoginController {
    private final LoginService loginService;
    private final JWTService jwtService;

    public LoginController(LoginService loginService, JWTService jwtService) {
        this.loginService = loginService;
        this.jwtService = jwtService;
    }

    @PostMapping
    public ResponseEntity<String> loginUser(@RequestBody @Valid AuthRequest req) {
        try {
            User user = loginService.login(req.getUsername(), req.getPassword());
            UUID jti = UUID.randomUUID();
            boolean registered = loginService.registerLogin(jti, user);
            if (registered) {
                Map<String, Object> res = new HashMap<>();
                res.put("access_token",jwtService.generateAccessToken(jti, user) );
                return new ResponseEntity(res, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Error with database", HttpStatus.BAD_GATEWAY);
            }
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("Wrong username/password", HttpStatus.UNAUTHORIZED);
        }
    }

}
