package com.example.sge.repository;

import com.example.sge.model.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.sge.model.Module;

import java.util.List;

@Repository
public interface EtudiantRepository
        extends JpaRepository<Etudiant, Long> {

    List<Etudiant> findByGroupe(String groupe);

    List<Etudiant> findByNomContainingIgnoreCase(String nom);

    List<Etudiant> findByMoyenneGreaterThanEqual(double seuil);

    @Query("SELECT e FROM Etudiant e WHERE e.moyenne >= :seuil " +
            "ORDER BY e.moyenne DESC")
    List<Etudiant> findMeilleurs(@Param("seuil") double seuil);
}