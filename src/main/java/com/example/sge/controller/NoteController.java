package com.example.sge.controller;

import com.example.sge.model.Note;
import com.example.sge.service.NoteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Note ajouter(@RequestBody Note note) {
        return noteService.ajouterNote(note);
    }

    @GetMapping("/etudiant/{id}")
    public List<Note> parEtudiant(@PathVariable Long id) {
        return noteService.listerParEtudiant(id);
    }

    @GetMapping("/module/{id}")
    public List<Note> parModule(@PathVariable Long id) {
        return noteService.listerParModule(id);
    }

    @GetMapping("/moyenne/{id}")
    public Double moyenne(@PathVariable Long id) {
        return noteService.calculerMoyenne(id);
    }

    @PutMapping("/{id}")
    public Note modifier(@PathVariable Long id, @RequestBody Note note) {
        return noteService.modifier(id, note);
    }

    @DeleteMapping("/{id}")
    public void supprimer(@PathVariable Long id) {
        noteService.supprimer(id);
    }
}