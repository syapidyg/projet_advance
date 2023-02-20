package com.advance.pharmacie.model;


import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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


    @ManyToMany(mappedBy = "produits"
            ,fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    private Set<Depot> depots = new HashSet<>();


    @ManyToMany(mappedBy = "produits"
            ,fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    private Set<Commande> commandes = new HashSet<>();
}
