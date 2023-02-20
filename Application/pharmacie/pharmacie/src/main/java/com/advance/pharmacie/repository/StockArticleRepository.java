package com.advance.pharmacie.repository;

import com.advance.pharmacie.model.StockArticle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockArticleRepository extends JpaRepository<StockArticle, Long> {
}
