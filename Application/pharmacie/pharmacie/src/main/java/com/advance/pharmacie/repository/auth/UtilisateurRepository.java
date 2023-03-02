package com.advance.pharmacie.repository.auth;

import com.advance.pharmacie.model.auth.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    Optional<Utilisateur> findByUsername(String username);

     boolean existsByUsername(String Username);
}
