package com.example.sge.controller;

import com.example.sge.service.TableauDeBordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private TableauDeBordService tableauDeBordService;

    @PostMapping("/filiere")
    public ResponseEntity<String> changerFiliere(
            @RequestParam String nom) {
        tableauDeBordService.setFiliereActive(nom);
        return ResponseEntity.ok("Filiere active : " + nom);
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> stats() {
        tableauDeBordService.incrementerConsultations();
        return ResponseEntity.ok(Map.of(
                "filiereActive",
                tableauDeBordService.getFiliereActive(),
                "consultations",
                tableauDeBordService.getConsultations()
        ));
    }

    @DeleteMapping("/reset")
    public ResponseEntity<String> reset() {
        tableauDeBordService.reinitialiser();
        return ResponseEntity.ok("Session reinitalisee.");
    }
}