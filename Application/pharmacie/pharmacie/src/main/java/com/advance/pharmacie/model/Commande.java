package com.advance.pharmacie.model;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "APP_COMMANDE")
public class Commande extends  AuditEntity{

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


    public static final class CommandeBuilder {
        private Long id;
        private String type;
        private String statut;
        private Fournisseur fournisseur;
        private Client client;
        private List<LigneCommande> ligneCommandesommande;
        private List<Reglement> reglements;

        private CommandeBuilder() {
        }

        public static CommandeBuilder aCommande() {
            return new CommandeBuilder();
        }

        public CommandeBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public CommandeBuilder type(String type) {
            this.type = type;
            return this;
        }

        public CommandeBuilder statut(String statut) {
            this.statut = statut;
            return this;
        }

        public CommandeBuilder fournisseur(Fournisseur fournisseur) {
            this.fournisseur = fournisseur;
            return this;
        }

        public CommandeBuilder client(Client client) {
            this.client = client;
            return this;
        }

        public CommandeBuilder ligneCommandesommande(List<LigneCommande> ligneCommandesommande) {
            this.ligneCommandesommande = ligneCommandesommande;
            return this;
        }

        public CommandeBuilder reglements(List<Reglement> reglements) {
            this.reglements = reglements;
            return this;
        }

        public Commande build() {
            Commande commande = new Commande();
            commande.setId(id);
            commande.setType(type);
            commande.setStatut(statut);
            commande.setFournisseur(fournisseur);
            commande.setClient(client);
            commande.setLigneCommandesommande(ligneCommandesommande);
            commande.setReglements(reglements);
            return commande;
        }
    }
}
