package com.advance.pharmacie.model;


import com.advance.pharmacie.model.lnk.LigneCommande;
import com.advance.pharmacie.model.lnk.StockArticle;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

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

    @Column(unique = true)
    private String code;

    private String dci;

    private String forme;

    private String categorie;

    private String dosage;

    private Long pa;

    private Long pv;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ManyToOne(targetEntity = Famille.class)
    @JoinColumn(name ="id_famille", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_famille_produit"))
    private Famille famille;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ToString.Exclude
    @OneToMany(targetEntity = StockArticle .class, mappedBy = "produit")
    private List<StockArticle> stockArticles = new ArrayList<>();


    @OneToMany(mappedBy = "produit")
    private List<LigneCommande> ligneCommandes = new ArrayList<>();


    public static final class ProduitBuilder {
        private Long id;
        private String code;
        private String dci;
        private String forme;
        private String categorie;
        private String dosage;
        private Long pa;
        private Long pv;
        private Famille famille;

        private ProduitBuilder() {
        }

        public static ProduitBuilder aProduit() {
            return new ProduitBuilder();
        }

        public ProduitBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ProduitBuilder code(String code) {
            this.code = code;
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

        public ProduitBuilder categorie(String categorie) {
            this.categorie = categorie;
            return this;
        }

        public ProduitBuilder dosage(String dosage) {
            this.dosage = dosage;
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

        public Produit build() {
            Produit produit = new Produit();
            produit.setId(id);
            produit.setCode(code);
            produit.setDci(dci);
            produit.setForme(forme);
            produit.setCategorie(categorie);
            produit.setDosage(dosage);
            produit.setPa(pa);
            produit.setPv(pv);
            produit.setFamille(famille);
            return produit;
        }
    }
}
