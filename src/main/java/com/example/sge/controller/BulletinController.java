package com.example.sge.controller;

import com.example.sge.dto.BulletinDTO;
import com.example.sge.model.Etudiant;
import com.example.sge.model.Note;
import com.example.sge.repository.EtudiantRepository;
import com.example.sge.service.EtudiantService;
import com.example.sge.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/bulletins")
public class BulletinController {

    @Autowired
    private EtudiantService etudiantService;

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private NoteService noteService;

    @GetMapping("/bulletin/{id}")
    public String afficherBulletin(@PathVariable Long id, Model model) {

        Etudiant etudiant = etudiantRepository.findById(id).orElse(null);

        List<Note> notes = etudiant.getNotes();

        double total = 0;
        int coefTotal = 0;

        for(Note n : notes){

            total += n.getValeur() * n.getModule().getCoefficient();

            coefTotal += n.getModule().getCoefficient();
        }

        double moyenne = total / coefTotal;

        model.addAttribute("etudiant", etudiant);
        model.addAttribute("notes", notes);
        model.addAttribute("moyenne", moyenne);

        return "detail";
    }








    @GetMapping
    public String listeBulletins(Model model) {

        List<Etudiant> etudiants = etudiantService.listerTous();
        List<BulletinDTO> bulletins = new ArrayList<>();

        for (Etudiant e : etudiants) {

            List<Note> notes = noteService.listerParEtudiant(e.getId());

            double moyenne = noteService.calculerMoyenne(e.getId());
            String mention = noteService.calculerMention(moyenne);
            boolean admis = moyenne >= 10;

            bulletins.add(new BulletinDTO(e, notes, moyenne, mention, admis));
        }

        model.addAttribute("bulletins", bulletins);


        return "bulletins/liste";
    }


    @GetMapping("/{id}")
    public String detailBulletin(@PathVariable Long id, Model model) {

        Etudiant etudiant = etudiantService.trouverParId(id)
                .orElseThrow(() -> new RuntimeException("Étudiant introuvable"));

        List<Note> notes = noteService.listerParEtudiant(id);

        double moyenne = noteService.calculerMoyenne(id);
        String mention = noteService.calculerMention(moyenne);
        boolean admis = moyenne >= 10;

        double totalSomme = notes.stream()
                .mapToDouble(n -> n.getValeur() * n.getModule().getCoefficient())
                .sum();

        int totalCoef = notes.stream()
                .mapToInt(n -> n.getModule().getCoefficient())
                .sum();

        BulletinDTO bulletin = new BulletinDTO(
                etudiant,
                notes,
                moyenne,
                mention,
                admis
        );

        model.addAttribute("bulletin", bulletin);
        model.addAttribute("totalSomme", totalSomme);
        model.addAttribute("totalCoef", totalCoef);


        return "bulletins/detail";
    }
}