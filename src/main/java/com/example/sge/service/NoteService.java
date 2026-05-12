package com.example.sge.service;

import com.example.sge.model.Etudiant;
import com.example.sge.model.Module;
import com.example.sge.model.Note;
import com.example.sge.repository.EtudiantRepository;
import com.example.sge.repository.ModuleRepository;
import com.example.sge.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private final EtudiantRepository etudiantRepository;
    private final ModuleRepository moduleRepository;

    public NoteService(
            NoteRepository noteRepository,
            EtudiantRepository etudiantRepository,
            ModuleRepository moduleRepository) {
        this.noteRepository = noteRepository;
        this.etudiantRepository = etudiantRepository;
        this.moduleRepository = moduleRepository;
    }

    public Note ajouterNote(Note note) {
        if (note.getValeur() < 0 || note.getValeur() > 20) {
            throw new RuntimeException("La note doit etre entre 0 et 20");
        }
        Long etudiantId = note.getEtudiant().getId();
        Long moduleId = note.getModule().getId();
        Etudiant etudiant = etudiantRepository.findById(etudiantId)
                .orElseThrow(() -> new RuntimeException("Etudiant introuvable"));
        Module module = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new RuntimeException("Module introuvable"));
        note.setEtudiant(etudiant);
        note.setModule(module);
        Note saved = noteRepository.save(note);
        mettreAJourMoyenne(etudiantId);
        return saved;
    }
    public List<Note> listerToutes() {
        return noteRepository.findAll();
    }

    public List<Note> listerParEtudiant(Long etudiantId) {
        return noteRepository.findByEtudiantId(etudiantId);
    }

    public List<Note> listerParModule(Long moduleId) {
        return noteRepository.findByModuleId(moduleId);
    }

    public Double calculerMoyenne(Long etudiantId) {
        Double moyenne = noteRepository.calculerMoyenne(etudiantId);
        return moyenne != null ? moyenne : 0.0;
    }

    public void mettreAJourMoyenne(Long etudiantId) {
        Etudiant etudiant = etudiantRepository.findById(etudiantId)
                .orElseThrow();
        Double moyenne = calculerMoyenne(etudiantId);
        etudiant.setMoyenne(moyenne);
        etudiantRepository.save(etudiant);
    }

    public Note modifier(Long id, Note nouvelleNote) {
        Note note = noteRepository.findById(id)
                .orElseThrow();
        note.setValeur(nouvelleNote.getValeur());
        Note updated = noteRepository.save(note);
        mettreAJourMoyenne(note.getEtudiant().getId());
        return updated;
    }

    public void supprimer(Long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow();
        Long etudiantId = note.getEtudiant().getId();
        noteRepository.delete(note);
        mettreAJourMoyenne(etudiantId);
    }

    public String calculerMention(double moyenne) {
        if (moyenne >= 16) return "Trés Bien";
        if (moyenne >= 14) return "Bien";
        if (moyenne >= 12) return "Assez Bien";
        if (moyenne >= 10) return "Passable";
        return "Ajourné";
    }

}