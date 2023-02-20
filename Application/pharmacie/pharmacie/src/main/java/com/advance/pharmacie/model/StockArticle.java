package com.advance.pharmacie.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "LNK_STOCK_ARTICLE")
public class StockArticle extends  AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(targetEntity = Depot.class)
    @JoinColumn(name = "depot_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_Stock_Article"))
    private Depot depot;

    @ManyToOne(targetEntity = Produit.class)
    @JoinColumn(name = "produit_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_Produit_Article"))
    private Produit produit;
}
