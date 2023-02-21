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

    private String status;

    private String Quantité;

    @ManyToOne(targetEntity = Commande.class)
    @JoinColumn(name = "commande_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_commande_ligne"))
    private Commande commande;


    @ManyToOne(targetEntity = Produit.class)
    @JoinColumn(name = "produit_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_ligne_produit"))
    private Produit produit;

    public static final class LigneCommandeBuilder {
        private Long id;
        private String status;
        private Commande commande;
        private Produit produit;

        private LigneCommandeBuilder() {
        }

        public static LigneCommandeBuilder aLigneCommande() {
            return new LigneCommandeBuilder();
        }

        public LigneCommandeBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public LigneCommandeBuilder status(String status) {
            this.status = status;
            return this;
        }

        public LigneCommandeBuilder commande(Commande commande) {
            this.commande = commande;
            return this;
        }

        public LigneCommandeBuilder produit(Produit produit) {
            this.produit = produit;
            return this;
        }

        public LigneCommande build() {
            LigneCommande ligneCommande = new LigneCommande();
            ligneCommande.setId(id);
            ligneCommande.setStatus(status);
            ligneCommande.setCommande(commande);
            ligneCommande.setProduit(produit);
            return ligneCommande;
        }
    }
}