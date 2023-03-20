package com.advance.pharmacie.repository.lnk;

import com.advance.pharmacie.model.Commande;
import com.advance.pharmacie.model.lnk.LigneCommande;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LigneCommandeRepository extends JpaRepository<LigneCommande, Long> {
    List<LigneCommande> findByCommandeId(Long id);

}
