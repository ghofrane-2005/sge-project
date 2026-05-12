package com.example.sge.controller;

import com.example.sge.model.Etudiant;
import com.example.sge.model.Module;
import com.example.sge.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/modules")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @PostMapping
    public ResponseEntity<Module> ajouter(@RequestBody Module m) {
        return ResponseEntity.status(201)
                .body(moduleService.ajouter(m));
    }

    @GetMapping
    public ResponseEntity<List<Module>> listerTous() {
        return ResponseEntity.ok(moduleService.listerTous());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        moduleService.supprimer(id);
        return ResponseEntity.noContent().build();
    }
}