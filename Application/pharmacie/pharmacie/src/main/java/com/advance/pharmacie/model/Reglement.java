package com.advance.pharmacie.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Reglement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @ManyToOne(targetEntity = Utilisateur.class)
    @JoinColumn(name ="id_famille", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_famille_produit"))
    private Famille famille;
}
