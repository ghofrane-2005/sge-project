package com.example.sge.controller;

import com.example.sge.model.Filiere;
import com.example.sge.service.FiliereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filieres")
public class FiliereController {

    @Autowired
    private FiliereService filiereService;

    @PostMapping
    public ResponseEntity<Filiere> ajouter(
            @RequestBody Filiere f) {
        return ResponseEntity.status(201)
                .body(filiereService.ajouter(f));
    }

    @GetMapping
    public ResponseEntity<List<Filiere>> listerTous() {
        return ResponseEntity.ok(filiereService.listerTous());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Filiere> trouverParId(
            @PathVariable Long id) {
        return filiereService.trouverParId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Filiere> modifier(
            @PathVariable Long id,
            @RequestBody Filiere f) {
        return ResponseEntity.ok(filiereService.modifier(id, f));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(
            @PathVariable Long id) {
        filiereService.supprimer(id);
        return ResponseEntity.noContent().build();
    }
}