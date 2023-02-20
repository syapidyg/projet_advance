package com.advance.pharmacie.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "LNK_LIGNE_COMMANDE")
public class LigneCommande extends AuditEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Commande.class)
    @JoinColumn(name = "commande_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_commande_ligne"))
    private Commande commande;


    @ManyToOne(targetEntity = Produit.class)
    @JoinColumn(name = "produit_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_ligne_produit"))
    private Produit produit;

}