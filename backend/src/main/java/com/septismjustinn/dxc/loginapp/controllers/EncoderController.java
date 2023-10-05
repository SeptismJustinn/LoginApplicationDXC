package com.septismjustinn.dxc.loginapp.controllers;

import com.septismjustinn.dxc.loginapp.services.Encoder;
import com.septismjustinn.dxc.loginapp.validators.DecodeRequest;
import com.septismjustinn.dxc.loginapp.validators.EncodeRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/protected/encoder")
@CrossOrigin
public class EncoderController {
    @GetMapping
    public ResponseEntity<Map<String, Object>> decode(@RequestBody @Valid DecodeRequest req) {
        Map<String, Object> res = new HashMap<>();
        res.put("content", Encoder.decode(req.getPlainText()));
        res.put("status", true);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> encode(@RequestBody @Valid EncodeRequest req) {
        Map<String, Object> res = new HashMap<>();
        Encoder encoder = new Encoder(req.getOffset().charAt(0));
        res.put("content", encoder.encode(req.getPlainText()));
        res.put("status", true);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
