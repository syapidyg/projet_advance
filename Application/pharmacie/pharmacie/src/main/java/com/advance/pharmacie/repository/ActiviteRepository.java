package com.advance.pharmacie.repository;

import com.advance.pharmacie.model.Activite;
import com.advance.pharmacie.model.Commande;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ActiviteRepository extends JpaRepository<Activite, Long> {

    Page<Activite> findByNomUtilisateur(String nomUtilisateur, Pageable page);

//    SELECT a FROM Activite a JOIN u.activites a WHERE a.utilisateur.nomUtilisateur = :nomUtilisateur
//
//    @Query("SELECT a from Activite a JOIN Utilisateur u where a.nomUtilisateur = u. "
//            "ORDER BY c.id DESC")
//    Page<Commande> findAllCommande(String type, String token, Pageable page);





}
