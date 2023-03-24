package com.advance.pharmacie.repository;

import com.advance.pharmacie.model.VariableGlobale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VariableGlobaleRepository extends JpaRepository<VariableGlobale, Long> {
    VariableGlobale findByCle(String cle);
}
