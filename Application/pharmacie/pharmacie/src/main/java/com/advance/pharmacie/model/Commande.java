package com.advance.pharmacie.model;


import com.advance.pharmacie.model.lnk.LigneCommande;
import com.advance.pharmacie.model.lnk.Reglement;
import com.advance.pharmacie.util.StatutCommande;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@Data
@Table(name = "APP_COMMANDE")
public class Commande extends  AuditEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long pt;

    private String type;

    private StatutCommande statut;

    private String document;

    @ManyToOne(targetEntity = Fournisseur.class)
    @JoinColumn(name ="id_fournisseur", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_fournisseur_commande"))
    private Fournisseur fournisseur;

    @ManyToOne(targetEntity = Client.class)
    @JoinColumn(name ="id_client", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_client_commande"))
    private Client client;

    @OneToMany(mappedBy = "commande")
    private List<LigneCommande> ligneCommande = new ArrayList<>();

    @OneToMany(mappedBy = "commande")
    private List<Reglement> reglements = new ArrayList<>();


    public static final class CommandeBuilder {
        private Long id;
        private Long pt;
        private String type;
        private StatutCommande statut;
        private String document;
        private Fournisseur fournisseur;
        private Client client;
        private List<LigneCommande> ligneCommande;
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

        public CommandeBuilder pt(Long pt) {
            this.pt = pt;
            return this;
        }

        public CommandeBuilder type(String type) {
            this.type = type;
            return this;
        }

        public CommandeBuilder statut(StatutCommande statut) {
            this.statut = statut;
            return this;
        }

        public CommandeBuilder document(String document) {
            this.document = document;
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

        public CommandeBuilder ligneCommande(List<LigneCommande> ligneCommande) {
            this.ligneCommande = ligneCommande;
            return this;
        }

        public CommandeBuilder reglements(List<Reglement> reglements) {
            this.reglements = reglements;
            return this;
        }

        public Commande build() {
            Commande commande = new Commande();
            commande.setId(id);
            commande.setPt(pt);
            commande.setType(type);
            commande.setStatut(statut);
            commande.setDocument(document);
            commande.setFournisseur(fournisseur);
            commande.setClient(client);
            commande.setLigneCommande(ligneCommande);
            commande.setReglements(reglements);
            return commande;
        }
    }
}
