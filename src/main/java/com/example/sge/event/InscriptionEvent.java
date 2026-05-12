package com.example.sge.event;

import org.springframework.context.ApplicationEvent;

public class InscriptionEvent extends ApplicationEvent {

    private final String nomEtudiant;
    private final String filiere;

    public InscriptionEvent(Object source,
                            String nomEtudiant,
                            String filiere) {
        super(source);
        this.nomEtudiant = nomEtudiant;
        this.filiere = filiere;
    }

    public String getNomEtudiant() { return nomEtudiant; }
    public String getFiliere()     { return filiere; }
}