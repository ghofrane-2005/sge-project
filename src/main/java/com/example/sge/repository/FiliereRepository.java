package com.example.sge.repository;

import com.example.sge.model.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FiliereRepository
        extends JpaRepository<Filiere, Long> { }