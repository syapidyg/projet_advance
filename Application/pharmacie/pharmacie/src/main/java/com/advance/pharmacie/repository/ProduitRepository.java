package com.advance.pharmacie.repository;

import com.advance.pharmacie.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository extends JpaRepository<Produit, Long> {
}
