package com.advance.pharmacie.model;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "APP_PRODUIT")
public class Produit {
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
}
