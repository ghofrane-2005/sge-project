package com.example.sge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "modules")
public class Module {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;
    private String nom;
    private String code;
    private Integer    coefficient;

    @ManyToOne
    @JoinColumn(name = "filiere_id")
    private Filiere filiere;


    public Module() {}

    public Long getId()                  { return id; }
    public void setId(Long id)           { this.id = id; }

    public String getNom()               { return nom; }
    public void setNom(String nom)       { this.nom = nom; }

    public String getCode()              { return code; }
    public void setCode(String code)     { this.code = code; }

    public Integer getCoefficient()          { return coefficient; }
    public void setCoefficient(int c)    { this.coefficient = c; }

    public Filiere getFiliere()          { return filiere; }
    public void setFiliere(Filiere f)    { this.filiere = f; }

}