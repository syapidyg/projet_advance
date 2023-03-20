package com.advance.pharmacie.repository;

import com.advance.pharmacie.model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommandeRepository extends JpaRepository<Commande, Long> {

    List<Commande> findByType(String type);

}
