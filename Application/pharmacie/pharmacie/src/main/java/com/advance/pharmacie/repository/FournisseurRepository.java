package com.advance.pharmacie.repository;

import com.advance.pharmacie.model.Fournisseur;
import com.advance.pharmacie.model.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FournisseurRepository extends JpaRepository<Fournisseur, Long> {

    @Query("SELECT f from Fournisseur f where f.name like :token ORDER BY f.id DESC ")
    Page<Fournisseur> findAllFournisseur(String token, Pageable page);

}
