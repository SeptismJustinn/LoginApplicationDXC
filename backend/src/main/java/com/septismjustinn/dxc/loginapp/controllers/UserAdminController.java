package com.septismjustinn.dxc.loginapp.controllers;

import com.septismjustinn.dxc.loginapp.models.User;
import com.septismjustinn.dxc.loginapp.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/protected/admin")
public class UserAdminController {
    private final UserService userService;

    public UserAdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllUsers() {
        Map<String, Object> res = new HashMap<>();
        try {
            List<User> allUsers = userService.getAllUsers();
            res.put("data", allUsers);
            res.put("ok", true);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            res.put("data", "Error getting all users");
            res.put("ok", false);
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
    }
}
