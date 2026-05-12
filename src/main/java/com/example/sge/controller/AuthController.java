package com.example.sge.controller;

import com.example.sge.dto.AuthResponse;
import com.example.sge.dto.LoginRequest;
import com.example.sge.model.Role;
import com.example.sge.model.Utilisateur;
import com.example.sge.repository.UtilisateurRepository;
import com.example.sge.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UtilisateurRepository utilisateurRepo;
    @Autowired private JwtService jwtService;
    @Autowired private AuthenticationManager authManager;
    @Autowired private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody LoginRequest req) {
        if (utilisateurRepo.findByUsername(
                req.getUsername()).isPresent())
            return ResponseEntity.badRequest()
                    .body("Username deja utilise");
        Utilisateur u = new Utilisateur();
        u.setUsername(req.getUsername());
        u.setPassword(
                passwordEncoder.encode(req.getPassword()));
        u.setRole(Role.USER);
        utilisateurRepo.save(u);
        return ResponseEntity.status(201).body(
                new AuthResponse(
                        jwtService.generateToken(u),
                        u.getRole().name()));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest req) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getUsername(), req.getPassword()));
        Utilisateur u = utilisateurRepo
                .findByUsername(req.getUsername()).orElseThrow();
        return ResponseEntity.ok(
                new AuthResponse(
                        jwtService.generateToken(u),
                        u.getRole().name()));
    }
    @PostMapping("/create-admin")
    public String createAdmin() {
        Utilisateur u = new Utilisateur();
        u.setUsername("admin");
        u.setPassword(passwordEncoder.encode("admin123"));
        u.setRole(Role.ADMIN);

        utilisateurRepo.save(u);
        return "admin created";
    }
}
