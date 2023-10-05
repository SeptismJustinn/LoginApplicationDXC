package com.septismjustinn.dxc.loginapp.controllers.auth;

import com.septismjustinn.dxc.loginapp.models.User;
import com.septismjustinn.dxc.loginapp.services.UserService;
import com.septismjustinn.dxc.loginapp.validators.RegisterRequest;
import jakarta.validation.Valid;
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

    /**
     * Endpoint to register user
     * @param req Request body containing valid name, username and password
     * @return Response {status: true, content: created user object} or {status: false, message: error message}
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> addNewUser(@RequestBody @Valid RegisterRequest req) {
        Map<String, Object> res = new HashMap<>();
        try {
            User newUser = userService.createUser(req.getName(), req.getUsername(), req.getPassword());
            res.put("content", newUser);
            res.put("status", true);
            return new ResponseEntity(res, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            res.put("message", "Error registering");
            res.put("status", false);
            return new ResponseEntity(res, HttpStatus.BAD_REQUEST);
        }
    }
}
