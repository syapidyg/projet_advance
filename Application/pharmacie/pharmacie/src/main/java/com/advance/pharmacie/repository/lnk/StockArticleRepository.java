package com.advance.pharmacie.repository.lnk;

import com.advance.pharmacie.model.Commande;
import com.advance.pharmacie.model.Depot;
import com.advance.pharmacie.model.Produit;
import com.advance.pharmacie.model.lnk.StockArticle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StockArticleRepository extends JpaRepository<StockArticle, Long> {

     Optional<StockArticle> findByDepotAndProduit(Depot depot, Produit produit);

     @Query("SELECT c from StockArticle c where c.produit.dci like :token ")
     Page<StockArticle> findAllStockArticle(String token, Pageable page);

}
