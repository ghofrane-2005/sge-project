package com.example.sge.model;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "filiere")
public class Filiere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;
    private String nom;
    private String niveau;
    private Integer    capacite;

    public Filiere() {}

    public Filiere(Long id, String nom,
                   String niveau, Integer capacite) {
        this.id       = id;
        this.nom      = nom;
        this.niveau   = niveau;
        this.capacite = capacite;
    }

    public Long getId()              { return id; }
    public void setId(Long id)       { this.id = id; }

    public String getNom()           { return nom; }
    public void setNom(String nom)   { this.nom = nom; }

    public String getNiveau()            { return niveau; }
    public void setNiveau(String niveau) { this.niveau = niveau; }

    public Integer getCapacite()         { return capacite; }
    public void setCapacite(int c)   { this.capacite = c; }
}