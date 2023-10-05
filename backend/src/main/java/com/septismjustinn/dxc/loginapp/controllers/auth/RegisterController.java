package com.septismjustinn.dxc.loginapp.controllers.auth;

import com.septismjustinn.dxc.loginapp.models.User;
import com.septismjustinn.dxc.loginapp.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/public/register")
public class RegisterController {
    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> addNewUser(@RequestBody Map<String, String> req) {
        Map<String, Object> res = new HashMap<>();
        try {
            User newUser = userService.createUser(req.get("name"), req.get("username"), req.get("password"));
            res.put("data", newUser);
            res.put("ok", true);
            return new ResponseEntity(res, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            res.put("data", "Error registering");
            res.put("ok", false);
            return new ResponseEntity(res, HttpStatus.BAD_REQUEST);
        }
    }
}
