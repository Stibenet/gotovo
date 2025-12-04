package ru.gotovoweb.gotovobackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.gotovoweb.gotovobackend.config.JwtUtil;
import ru.gotovoweb.gotovobackend.dto.AuthResponse;
import ru.gotovoweb.gotovobackend.dto.LoginRequest;
import ru.gotovoweb.gotovobackend.dto.RegisterRequest;
import ru.gotovoweb.gotovobackend.entity.Role;
import ru.gotovoweb.gotovobackend.entity.User;
import ru.gotovoweb.gotovobackend.repository.UserRepository;


@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public ResponseEntity<AuthResponse> register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body(new AuthResponse(null, "Email already exists"));
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER); // по умолчанию
        userRepository.save(user);
        String token = jwtUtil.generateToken(user.getEmail());
        AuthResponse response = new AuthResponse(token, "Registration successful");
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<AuthResponse> login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.badRequest().body(new AuthResponse(null, "Invalid credentials"));
        }
        String token = jwtUtil.generateToken(user.getEmail());
        AuthResponse response = new AuthResponse(token, "Login successful");
        return ResponseEntity.ok(response);
    }
}
