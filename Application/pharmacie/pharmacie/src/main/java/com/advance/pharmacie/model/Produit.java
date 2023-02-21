package com.advance.pharmacie.model;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "APP_PRODUIT")
public class Produit extends AuditEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dci;

    private String forme;

    private String dosage;

    private String rayon;

    private Long pa;

    private Long pv;

    @ManyToOne(targetEntity = Famille.class)
    @JoinColumn(name ="id_famille", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_famille_produit"))
    private Famille famille;


    @OneToMany(mappedBy = "produit")
    private List<StockArticle> stockArticles = new ArrayList<>();


    @OneToMany(mappedBy = "produit")
    private List<LigneCommande> ligneCommandes = new ArrayList<>();

    public static final class ProduitBuilder {
        private Long id;
        private String dci;
        private String forme;
        private String dosage;
        private String rayon;
        private Long pa;
        private Long pv;
        private Famille famille;
        private List<StockArticle> stockArticles;
        private List<LigneCommande> ligneCommandes;

        private ProduitBuilder() {
        }

        public static ProduitBuilder aProduit() {
            return new ProduitBuilder();
        }

        public ProduitBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ProduitBuilder dci(String dci) {
            this.dci = dci;
            return this;
        }

        public ProduitBuilder forme(String forme) {
            this.forme = forme;
            return this;
        }

        public ProduitBuilder dosage(String dosage) {
            this.dosage = dosage;
            return this;
        }

        public ProduitBuilder rayon(String rayon) {
            this.rayon = rayon;
            return this;
        }

        public ProduitBuilder pa(Long pa) {
            this.pa = pa;
            return this;
        }

        public ProduitBuilder pv(Long pv) {
            this.pv = pv;
            return this;
        }

        public ProduitBuilder famille(Famille famille) {
            this.famille = famille;
            return this;
        }

        public ProduitBuilder stockArticles(List<StockArticle> stockArticles) {
            this.stockArticles = stockArticles;
            return this;
        }

        public ProduitBuilder ligneCommandes(List<LigneCommande> ligneCommandes) {
            this.ligneCommandes = ligneCommandes;
            return this;
        }

        public Produit build() {
            Produit produit = new Produit();
            produit.setId(id);
            produit.setDci(dci);
            produit.setForme(forme);
            produit.setDosage(dosage);
            produit.setRayon(rayon);
            produit.setPa(pa);
            produit.setPv(pv);
            produit.setFamille(famille);
            produit.setStockArticles(stockArticles);
            produit.setLigneCommandes(ligneCommandes);
            return produit;
        }
    }
}
