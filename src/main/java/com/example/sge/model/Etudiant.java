package com.example.sge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "etudiants")
public class Etudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long      id;
    private String    nom;
    private String    prenom;
    private String    email;
    private String    cin;
    private LocalDate dateNaissance;
    private String    groupe;
    private double    moyenne;

    @ManyToOne
    @JoinColumn(name = "filiere_id")
    private Filiere filiere;

    @JsonIgnore
    @OneToMany ( mappedBy = "etudiant" , cascade = CascadeType . ALL )
    private List< Note > notes = new ArrayList< >() ;

    public Etudiant() {}

    public Long getId()                       { return id; }
    public void setId(Long id)                { this.id = id; }

    public String getNom()                    { return nom; }
    public void setNom(String nom)            { this.nom = nom; }

    public String getPrenom()                 { return prenom; }
    public void setPrenom(String prenom)      { this.prenom = prenom; }

    public String getEmail()                  { return email; }
    public void setEmail(String email)        { this.email = email; }

    public String getCin()                    { return cin; }
    public void setCin(String cin)            { this.cin = cin; }

    public LocalDate getDateNaissance()       { return dateNaissance; }
    public void setDateNaissance(LocalDate d) { this.dateNaissance = d; }

    public String getGroupe()                 { return groupe; }
    public void setGroupe(String groupe)      { this.groupe = groupe; }

    public double getMoyenne()                { return moyenne; }
    public void setMoyenne(double moyenne)    { this.moyenne = moyenne; }

    public Filiere getFiliere()               { return filiere; }
    public void setFiliere(Filiere filiere)   { this.filiere = filiere; }

}