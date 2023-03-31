package com.advance.pharmacie.repository;

import com.advance.pharmacie.model.Commande;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface CommandeRepository extends JpaRepository<Commande, Long> {

    List<Commande> findByType(String type);

    Boolean existsByCode(String code);

    List<Commande> findByTypeOrTypeAndDocument(String type1, String type2, String document);

    @Query("SELECT c from Commande c where cast(c.date_creation as LocalDate)='2023-03-24' and c.document like :token AND c.type not like :type " +
            "ORDER BY c.id DESC")
    Page<Commande> findAllCommande(String type, String token, Pageable page);

    @Query("SELECT cf from Commande cf where cf.type= :type AND cf.client.name like :token OR cf.document like :document ORDER BY cf.id DESC")
    Page<Commande> findAllCommandeTypeClient(String type, String document, String token, Pageable page);

    @Query("SELECT cf from Commande cf where cf.type= :type AND cf.document like :token ORDER BY cf.id DESC")
    Page<Commande> findAllCommandeTypeStock(String type, String token, Pageable page);

    @Query("SELECT cf from Commande cf where cf.type= :type AND cf.fournisseur.name like :token ORDER BY cf.id DESC")
    Page<Commande> findAllCommandeTypeFournisseur(String type, String token, Pageable page);

    @Query(value = "SELECT * from app_commande as cf " +
            "LEFT JOIN app_client cl ON cl.id = cf.id_client " +
            "LEFT JOIN app_fournisseur co ON co.id = cf.id_fournisseur" +
            " where (cf.type= :type1 AND cf.document like :document) " +
            "AND CAST(cf.date_creation as Date) >= :dateDebut AND CAST(cf.date_creation as Date) <= :dateFin " +
            "AND (cl.name like :token OR co.name like :token) LIMIT :sizeOf " , nativeQuery = true
    )
    Page<Commande> findAllCommandeTypeOrTypeAndDocument(String type1, String document, String token, String dateDebut, String dateFin, Pageable page, int sizeOf);
//
//    @Query("SELECT cf from Commande cf where (cf.type= :type1 AND cf.document like :document) " +
//            //"AND (cf.date_creation >=  :dateDebut AND cf.date_creation <= :dateFin) " +
//            "AND (cf.client.name like :token OR cf.fournisseur.name like :token)"
//    )
//    Page<Commande> findAllCommandeTypeOrTypeAndDocument(String type1, String document, String token, LocalDateTime dateDebut, LocalDateTime dateFin, Pageable page);


}
