package com.example.sge.service;

import com.example.sge.model.Filiere;
import com.example.sge.repository.FiliereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FiliereService {

    @Autowired
    private FiliereRepository filiereRepository;

    public Filiere ajouter(Filiere f) {
        return
                filiereRepository.save(f);
    }

    public List<Filiere> listerTous() {
        return
                filiereRepository.findAll();
    }

    public Optional<Filiere> trouverParId(Long id) {

        return filiereRepository.findById(id);
    }

    public Filiere modifier(Long id, Filiere f) {
        return filiereRepository.findById(id).map(existing -> {
            existing.setNom(f.getNom());
            existing.setNiveau(f.getNiveau());
            existing.setCapacite(f.getCapacite());
            return filiereRepository.save(existing);
        }).orElseThrow(() ->
                new RuntimeException("Filiere introuvable : " + id));
    }

    public void supprimer(Long id) {

        filiereRepository.deleteById(id);
    }
}