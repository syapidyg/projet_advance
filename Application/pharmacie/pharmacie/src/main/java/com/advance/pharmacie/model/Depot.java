package com.advance.pharmacie.model;

import com.advance.pharmacie.model.lnk.StockArticle;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "APP_DEPOT")

public class Depot extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "depot")
    private List<StockArticle> stockArticles = new ArrayList<>();


    public static final class DepotBuilder {
        private Long id;
        private String name;
        private List<StockArticle> stockArticles;

        private DepotBuilder() {
        }

        public static DepotBuilder aDepot() {
            return new DepotBuilder();
        }

        public DepotBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public DepotBuilder name(String name) {
            this.name = name;
            return this;
        }

        public DepotBuilder stockArticles(List<StockArticle> stockArticles) {
            this.stockArticles = stockArticles;
            return this;
        }

        public Depot build() {
            Depot depot = new Depot();
            depot.setId(id);
            depot.setName(name);
            depot.setStockArticles(stockArticles);
            return depot;
        }
    }
}

