package com.example.sge.repository;

import com.example.sge.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long > {
    List< Note > findByEtudiantId (Long etudiantId ) ;
    List < Note > findByModuleId ( Long moduleId );
    @Query("SELECT AVG(n.valeur) FROM Note n WHERE n.etudiant.id = :id")
    Double calculerMoyenne(@Param("id") Long etudiantId);
    @Query("SELECT n FROM Note n JOIN FETCH n.etudiant JOIN FETCH n.module WHERE n.etudiant.id = :id")
    List<Note> findDetailsByEtudiant(@Param("id") Long id);
}
