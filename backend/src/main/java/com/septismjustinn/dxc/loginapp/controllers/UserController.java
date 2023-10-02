package com.septismjustinn.dxc.loginapp.controllers;

import com.septismjustinn.dxc.loginapp.models.User;
import com.septismjustinn.dxc.loginapp.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/protected/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> getUser(@RequestBody Map<String, String> req, @RequestHeader("Authorization") String token) {
        System.out.println("Getting " + req.get("username"));
        return ResponseEntity.of(userService.getUser(req.get("username")));
    }
}
