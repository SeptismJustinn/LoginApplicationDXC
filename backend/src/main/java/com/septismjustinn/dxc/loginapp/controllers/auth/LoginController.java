package com.septismjustinn.dxc.loginapp.controllers.auth;

import com.septismjustinn.dxc.loginapp.models.User;
import com.septismjustinn.dxc.loginapp.services.JWTService;
import com.septismjustinn.dxc.loginapp.services.LoginService;
import com.septismjustinn.dxc.loginapp.validators.AuthRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/public/login")
@CrossOrigin
public class LoginController {
    private final LoginService loginService;
    private final JWTService jwtService;

    public LoginController(LoginService loginService, JWTService jwtService) {
        this.loginService = loginService;
        this.jwtService = jwtService;
    }

    /**
     * Endpoint for user login
     * @param req Request body containing valid (non-null) username and password.
     * @return Response {status: true, content: user object} or {status: false, message: error message}
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody @Valid AuthRequest req) {
        Map<String, Object> res = new HashMap<>();
        try {
            User user = loginService.login(req.getUsername(), req.getPassword());
            UUID jti = UUID.randomUUID();
            boolean registered = loginService.registerLogin(jti, user);
            if (registered) {
                Map<String, Object> data = new HashMap<>();
                data.put("access_token",jwtService.generateAccessToken(jti, user));
                res.put("content", data);
                res.put("status", true);
                return new ResponseEntity(res, HttpStatus.OK);
            } else {
                throw new Exception("Unable to login user, check Logins table");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (e.getMessage() == "Bad credentials") {
                res.put("message", "Invalid username/password");
            } else {
                res.put("message", "Error with database");
            }
            res.put("status", false);
            return new ResponseEntity<>(res, HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> tokenLogin(@RequestHeader("Authorization") String header) {
        Map<String, Object> res = new HashMap<>();
        try {
            Optional<UserDetails> user = jwtService.isTokenValid(header.substring(7));
            if (user.isPresent()) {
                res.put("content", "Logged in with existing token");
                res.put("status", true);
                return new ResponseEntity(res, HttpStatus.OK);
            } else {
                throw new Exception("Invalid access token");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            res.put("message", "Error resuming old session, please log in normally");
            res.put("status", false);
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
    }

}
