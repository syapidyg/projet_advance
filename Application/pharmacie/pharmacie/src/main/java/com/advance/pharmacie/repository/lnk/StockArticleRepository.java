package com.advance.pharmacie.repository.lnk;

import com.advance.pharmacie.model.Depot;
import com.advance.pharmacie.model.Produit;
import com.advance.pharmacie.model.lnk.StockArticle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockArticleRepository extends JpaRepository<StockArticle, Long> {

     Optional<StockArticle> findByDepotAndProduit(Depot depot, Produit produit);
}
