package com.example.sge.controller;

import com.example.sge.model.Etudiant;
import com.example.sge.service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.data.domain.Page;

@RestController
@RequestMapping("/etudiants")
public class EtudiantController {

    @Autowired
    private EtudiantService etudiantService;

    @PostMapping
    public ResponseEntity<Etudiant> ajouter(
            @RequestBody Etudiant e) {
        return ResponseEntity.status(201)
                .body(etudiantService.ajouter(e));
    }

    @GetMapping
    public ResponseEntity<List<Etudiant>> listerTous() {
        return ResponseEntity.ok(etudiantService.listerTous());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Etudiant> trouverParId(
            @PathVariable Long id) {
        return etudiantService.trouverParId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Etudiant> modifier(
            @PathVariable Long id,
            @RequestBody Etudiant e) {
        return ResponseEntity.ok(etudiantService.modifier(id, e));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(
            @PathVariable Long id) {
        etudiantService.supprimer(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/recherche")
    public ResponseEntity<List<Etudiant>> rechercher(
            @RequestParam String nom) {
        return ResponseEntity.ok(
                etudiantService.rechercherParNom(nom));
    }

    @GetMapping("/groupe")
    public ResponseEntity<List<Etudiant>> parGroupe(
            @RequestParam String g) {
        return ResponseEntity.ok(
                etudiantService.trouverParGroupe(g));
    }

    @GetMapping("/admis")
    public ResponseEntity<List<Etudiant>> admis(
            @RequestParam double seuil) {
        return ResponseEntity.ok(
                etudiantService.trouverAdmis(seuil));
    }

    @GetMapping("/meilleurs")
    public ResponseEntity<List<Etudiant>> meilleurs(
            @RequestParam double seuil) {
        return ResponseEntity.ok(
                etudiantService.trouverMeilleurs(seuil));
    }

    @GetMapping("/page")
    public Page<Etudiant> getPage(
            @RequestParam(defaultValue = "0")  int page,
            @RequestParam(defaultValue = "5")  int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        return etudiantService
                .listerAvecPagination(page, size, sortBy);
    }
}