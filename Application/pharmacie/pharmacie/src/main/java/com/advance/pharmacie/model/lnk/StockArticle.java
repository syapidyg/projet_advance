package com.advance.pharmacie.model.lnk;

import com.advance.pharmacie.model.AuditEntity;
import com.advance.pharmacie.model.Depot;
import com.advance.pharmacie.model.Produit;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "LNK_STOCK_ARTICLE")
public class StockArticle extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long qte;

    private Long qteAlerte;

    private Long qteMinimale;

    private Long qteMaximale;


    @ManyToOne(targetEntity = Depot.class)
    @JoinColumn(name = "depot_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_Stock_Article"))
    private Depot depot;

    @ManyToOne(targetEntity = Produit.class)
    @JoinColumn(name = "produit_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_Produit_Article"))
    private Produit produit;


    public static final class StockArticleBuilder {
        private Long id;
        private Long qte;
        private Long qteAlerte;
        private Long qteMinimale;
        private Long qteMaximale;
        private Depot depot;
        private Produit produit;

        private StockArticleBuilder() {
        }

        public static StockArticleBuilder aStockArticle() {
            return new StockArticleBuilder();
        }

        public StockArticleBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public StockArticleBuilder qte(Long qte) {
            this.qte = qte;
            return this;
        }

        public StockArticleBuilder qteAlerte(Long qteAlerte) {
            this.qteAlerte = qteAlerte;
            return this;
        }

        public StockArticleBuilder qteMinimale(Long qteMinimale) {
            this.qteMinimale = qteMinimale;
            return this;
        }

        public StockArticleBuilder qteMaximale(Long qteMaximale) {
            this.qteMaximale = qteMaximale;
            return this;
        }

        public StockArticleBuilder depot(Depot depot) {
            this.depot = depot;
            return this;
        }

        public StockArticleBuilder produit(Produit produit) {
            this.produit = produit;
            return this;
        }

        public StockArticle build() {
            StockArticle stockArticle = new StockArticle();
            stockArticle.setId(id);
            stockArticle.setQte(qte);
            stockArticle.setQteAlerte(qteAlerte);
            stockArticle.setQteMinimale(qteMinimale);
            stockArticle.setQteMaximale(qteMaximale);
            stockArticle.setDepot(depot);
            stockArticle.setProduit(produit);
            return stockArticle;
        }
    }
}


