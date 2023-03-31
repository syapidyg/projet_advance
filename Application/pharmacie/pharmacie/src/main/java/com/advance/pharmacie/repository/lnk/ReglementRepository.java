package com.advance.pharmacie.repository.lnk;

import com.advance.pharmacie.model.Commande;
import com.advance.pharmacie.model.lnk.Reglement;
import com.advance.pharmacie.model.lnk.StockArticle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReglementRepository extends JpaRepository<Reglement, Long> {

    List<Reglement> findByCommandeType(String type);

//    Boolean existsByCode(String code);

    @Query("SELECT c from Reglement c where c.caisse.name like :token ")
    Page<Reglement> findAllReglement(String token, Pageable page);

    @Query("SELECT c from Reglement c where c.commande.type= :type OR c.caisse.name like :token ")
    Page<Reglement> findAllReglementType(String type, String token, Pageable page);


}
