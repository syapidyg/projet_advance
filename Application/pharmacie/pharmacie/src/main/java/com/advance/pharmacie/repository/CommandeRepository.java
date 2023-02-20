package com.advance.pharmacie.repository;

import com.advance.pharmacie.model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
}
