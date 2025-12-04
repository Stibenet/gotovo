package ru.gotovoweb.gotovobackend.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gotovoweb.gotovobackend.dto.AuthResponse;
import ru.gotovoweb.gotovobackend.dto.LoginRequest;
import ru.gotovoweb.gotovobackend.dto.RegisterRequest;
import ru.gotovoweb.gotovobackend.services.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequest request) {
        return authService.login(request);
    }
}
