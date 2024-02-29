package com.ngleanhvu.controller;

import com.ngleanhvu.dto.LoginDTO;
import com.ngleanhvu.dto.RegisterDTO;
import com.ngleanhvu.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {
    private AuthService authService;
    public  AuthController(AuthService authService){
        this.authService=authService;
    }
    @PostMapping(value = {"/login","/sign-in"})
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO){
        return ResponseEntity
                .ok()
                .header("custom-header","nguyen-vu")
                .body(authService.login(loginDTO));
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO){
        return ResponseEntity
                .ok()
                .header("custom-header","nguyen-vu")
                .body(authService.register(registerDTO));
    }
}
