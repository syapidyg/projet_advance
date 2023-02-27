package com.advance.pharmacie.repository;

import com.advance.pharmacie.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    Optional<Utilisateur> findByNom(String nom);

     boolean existsByEmail(String email);
}
