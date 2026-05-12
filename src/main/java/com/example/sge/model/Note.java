package com.example.sge.model;

import jakarta.persistence.*;

@Entity
@Table( name = " notes " )
public class Note {
    @Id
    @GeneratedValue( strategy = GenerationType . IDENTITY )
    private Long id ;
    private double valeur ; // note sur 20
    @ManyToOne
    @JoinColumn ( name = "etudiant_id ")
    private Etudiant etudiant ;
    @ManyToOne
    @JoinColumn ( name = "module_id ")
    private Module module ;

    public Note(){}

    public Note(double valeur, Etudiant etudiant, Module module) {
        this.valeur = valeur;
        this.etudiant = etudiant;
        this.module = module;
    }

    public void setId(Long id){ this.id=id;}
    public Long getId(){ return id;}
    public void setValeur(double v){this.valeur=v;}
    public double getValeur(){return valeur;}
    public void setEtudiant(Etudiant e){this.etudiant=e;}
    public Etudiant getEtudiant(){return etudiant;}
    public void setModule(Module m){this.module=m;}
    public Module getModule() {return module;}
}