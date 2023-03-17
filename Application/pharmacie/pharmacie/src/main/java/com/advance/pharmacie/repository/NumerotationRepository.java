package com.advance.pharmacie.repository;

import com.advance.pharmacie.model.Numerotation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NumerotationRepository extends JpaRepository<Numerotation, Long> {

    Optional<Numerotation> findByCode(String code);
}
