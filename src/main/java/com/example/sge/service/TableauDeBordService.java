package com.example.sge.service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
public class TableauDeBordService {

    private String filiereActive = "Aucune";
    private int consultations = 0;

    public void setFiliereActive(String filiere) {
        this.filiereActive = filiere;
    }

    public String getFiliereActive() {
        return filiereActive;
    }

    public void incrementerConsultations() {
        this.consultations++;
    }

    public int getConsultations() {
        return consultations;
    }

    public void reinitialiser() {
        this.filiereActive = "Aucune";
        this.consultations = 0;
    }
}