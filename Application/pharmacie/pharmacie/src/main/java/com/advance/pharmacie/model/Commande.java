package com.advance.pharmacie.model;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "commande")
    private List<LigneCommande> ligneCommandesommande = new ArrayList<>();

    @OneToMany(mappedBy = "commande")
    private List<Reglement> reglements = new ArrayList<>();
}
