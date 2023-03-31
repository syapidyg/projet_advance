package com.advance.pharmacie.repository;

import com.advance.pharmacie.model.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProduitRepository extends JpaRepository<Produit, Long> {

    Boolean existsByCode(String code);

    @Query("SELECT p from Produit p where p.dci like :token ")
    Page<Produit> findAllProduit(String token, Pageable page);

}
