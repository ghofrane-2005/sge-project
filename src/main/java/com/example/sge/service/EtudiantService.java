package com.example.sge.service;

import com.example.sge.event.InscriptionEvent;
import com.example.sge.model.Etudiant;
import com.example.sge.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;



@Service
public class EtudiantService {

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    public Etudiant ajouter(Etudiant e) {
        Etudiant saved = etudiantRepository.save(e);
        if (saved.getFiliere() != null) {
            publisher.publishEvent(new InscriptionEvent(this,
                    saved.getNom() + " " + saved.getPrenom(),
                    saved.getFiliere().getNom()));
        }
        return saved;
    }

    public List<Etudiant> listerTous() {

        return etudiantRepository.findAll();
    }

    public Optional<Etudiant> trouverParId(Long id) {

        return etudiantRepository.findById(id);
    }

    public Etudiant modifier(Long id, Etudiant e) {
        return etudiantRepository.findById(id).map(existing -> {
            existing.setNom(e.getNom());
            existing.setPrenom(e.getPrenom());
            existing.setEmail(e.getEmail());
            existing.setCin(e.getCin());
            existing.setGroupe(e.getGroupe());
            existing.setMoyenne(e.getMoyenne());
            existing.setFiliere(e.getFiliere());
            return etudiantRepository.save(existing);
        }).orElseThrow(() ->
                new RuntimeException("Etudiant introuvable : " + id));
    }

    public void supprimer(Long id) {

        etudiantRepository.deleteById(id);
    }

    public List<Etudiant> rechercherParNom(String nom) {
        return etudiantRepository
                .findByNomContainingIgnoreCase(nom);
    }

    public List<Etudiant> trouverParGroupe(String groupe) {

        return etudiantRepository.findByGroupe(groupe);
    }

    public List<Etudiant> trouverAdmis(double seuil) {
        return etudiantRepository
                .findByMoyenneGreaterThanEqual(seuil);
    }

    public List<Etudiant> trouverMeilleurs(double seuil) {

        return etudiantRepository.findMeilleurs(seuil);
    }
    public Page<Etudiant> listerAvecPagination(
            int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(
                page, size, Sort.by(sortBy));
        return etudiantRepository.findAll(pageable);
    }
}