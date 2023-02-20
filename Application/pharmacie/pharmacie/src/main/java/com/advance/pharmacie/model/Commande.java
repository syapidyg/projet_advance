package com.advance.pharmacie.model;


import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "APP_COMMANDE")
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    private String statut;

    @ManyToOne(targetEntity = Fournisseur.class)
    @JoinColumn(name ="id_fournisseur", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_fournisseur_commande"))
    private Fournisseur fournisseur;

    @ManyToOne(targetEntity = Client.class)
    @JoinColumn(name ="id_client", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_client_commande"))
    private Client client;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "LIGNE_COMMANDE",
            joinColumns = @JoinColumn(name = "commande_id"),
            inverseJoinColumns = @JoinColumn(name = "produit_id"))
    private Set<Produit> produits = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "REGLEMENT",
            joinColumns = @JoinColumn(name = "commande_id"),
            inverseJoinColumns = @JoinColumn(name = "caisse_id")

    )
    private Set<Caisse> caisses = new HashSet<>();
}
