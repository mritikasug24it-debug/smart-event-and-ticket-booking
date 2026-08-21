package com.smartevent.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.smartevent.entity.User;
import com.smartevent.repository.UserRepository;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest()
                    .body("Email already registered");
        }

        user.setRole("USER");

        userRepository.save(user);

        return ResponseEntity.ok("Registration successful");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {

        User existingUser = userRepository
                .findByEmail(user.getEmail())
                .orElse(null);

        if (existingUser == null) {
            return ResponseEntity.status(401)
                    .body("Invalid email or password");
        }

        if (!existingUser.getPassword().equals(user.getPassword())) {
            return ResponseEntity.status(401)
                    .body("Invalid email or password");
        }

        Map<String, Object> result = new HashMap<>();

        result.put("message", "Login successful");
        result.put("id", existingUser.getId());
        result.put("name", existingUser.getName());
        result.put("email", existingUser.getEmail());
        result.put("role", existingUser.getRole());

        return ResponseEntity.ok(result);
    }
}