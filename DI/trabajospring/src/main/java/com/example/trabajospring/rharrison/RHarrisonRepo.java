package com.example.trabajospring.rharrison;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RHarrisonRepo extends JpaRepository<RHarrison, Integer> {
    // <...Integer> es porque el @id de Persona es un Integer

}