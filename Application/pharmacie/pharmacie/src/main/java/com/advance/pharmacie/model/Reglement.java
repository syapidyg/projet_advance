package com.advance.pharmacie.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "LNK_REGLEMENT")
public class Reglement extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Utilisateur.class)
    @JoinColumn(name = "utilisateur_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_utilisateur_reglement"))
    private Utilisateur utilisateur;

    @ManyToOne(targetEntity = Commande.class)
    @JoinColumn(name = "commande_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_commande_reglement"))
    private Commande commande;

    @ManyToOne(targetEntity = Caisse.class)
    @JoinColumn(name = "caisse_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_caisse_reglement"))
    private Caisse caisse;
}
