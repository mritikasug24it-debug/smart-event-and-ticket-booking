package com.smartevent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.smartevent.entity.User;
import com.smartevent.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = {
	    "http://localhost:5173",
	    "http://127.0.0.1:5173",
	    "http://localhost:5174",
	    "http://127.0.0.1:5174",
	    "http://localhost:5175",
	    "http://127.0.0.1:5175",
	    "http://localhost:5176",
	    "http://127.0.0.1:5176",
	    "http://localhost:5177",
	    "http://127.0.0.1:5177"
	})
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("Email already registered");
        }

        user.setRole("USER");
        user.setOtpVerified(false);

        User savedUser = userRepository.save(user);

        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginUser) {

        User user = userRepository
                .findByEmail(loginUser.getEmail())
                .orElse(null);

        if (user == null) {
            return ResponseEntity
                    .badRequest()
                    .body("User not found");
        }

        if (!user.getPassword().equals(loginUser.getPassword())) {
            return ResponseEntity
                    .badRequest()
                    .body("Invalid password");
        }

        return ResponseEntity.ok(user);
    }
}