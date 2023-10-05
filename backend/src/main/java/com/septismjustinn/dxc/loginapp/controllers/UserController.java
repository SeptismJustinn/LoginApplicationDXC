package com.septismjustinn.dxc.loginapp.controllers;

import com.septismjustinn.dxc.loginapp.models.User;
import com.septismjustinn.dxc.loginapp.services.JWTService;
import com.septismjustinn.dxc.loginapp.services.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/protected/user")
public class UserController {
    private final UserService userService;
    private final JWTService jwtService;

    public UserController(UserService userService, JWTService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> getUser(@RequestBody Map<String, String> req) {
        Map<String, Object> res = new HashMap<>();
        try {
            Optional<User> target = userService.getUser(req.get("username"));
            if (target.isPresent()) {
                res.put("content", target.get());
                res.put("status", true);
                return new ResponseEntity<>(res, HttpStatus.OK);
            } else {
                throw new UsernameNotFoundException("Error finding username");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            res.put("message", "Error getting User");
            res.put("status", false);
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
    }
}
