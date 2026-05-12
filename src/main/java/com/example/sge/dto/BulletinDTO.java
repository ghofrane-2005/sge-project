package com.example.sge.dto;

import com.example.sge.model.Etudiant;
import com.example.sge.model.Note;
import java.util.List;

public class BulletinDTO {

    private Etudiant etudiant;
    private List<Note> notes;
    private double moyenneGenerale;
    private String mention;
    private boolean admis;

    public BulletinDTO(Etudiant etudiant, List<Note> notes, double moyenneGenerale, String mention, boolean admis) {
        this.etudiant = etudiant;
        this.notes = notes;
        this.moyenneGenerale = moyenneGenerale;
        this.mention = mention;
        this.admis = admis;
    }

    public Etudiant getEtudiant() {return etudiant;}
    public List<Note> getNotes() {return notes;}
    public double getMoyenneGenerale() {return moyenneGenerale;}
    public String getMention() {return mention;}
    public boolean isAdmis() {return admis;}
}