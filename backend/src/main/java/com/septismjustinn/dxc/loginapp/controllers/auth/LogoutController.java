package com.septismjustinn.dxc.loginapp.controllers.auth;

import com.septismjustinn.dxc.loginapp.services.JWTService;
import com.septismjustinn.dxc.loginapp.services.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/protected/logout")
public class LogoutController {
    private final JWTService jwtService;
    private final LoginService loginService;

    public LogoutController(JWTService jwtService, LoginService loginService) {
        this.jwtService = jwtService;
        this.loginService = loginService;
    }

    /**
     * Endpoint for user logout.
     * @param header Access token from Auth header
     * @return Response {status: true, content: loggedout message} or {status:false, message: error message}
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> logoutUser(@RequestHeader("Authorization") String header) {
        Map<String, Object> res = new HashMap<>();
        try {
            UUID tokenId = UUID.fromString(jwtService.extractClaim(header.substring(7)).getId());
            boolean loggedOut = loginService.registerLogout(tokenId);
            if (loggedOut) {
                res.put("content", "User successfully logged out");
                res.put("status", true);
                return new ResponseEntity<>(res, HttpStatus.OK);
            } else {
                throw new Exception("Error with database when logging out");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            res.put("message", "Error, user not logged out, please try again.");
            res.put("status", false);
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
    }
}
