package com.advance.pharmacie.model.lnk;


import com.advance.pharmacie.model.AuditEntity;
import com.advance.pharmacie.model.Caisse;
import com.advance.pharmacie.model.Commande;
import com.advance.pharmacie.model.auth.Utilisateur;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "LNK_REGLEMENT")
public class Reglement extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long montant;

    private Long rendu;

    private Long reste;

    @ManyToOne(targetEntity = Utilisateur.class)
    @JoinColumn(name = "utilisateur_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_utilisateur_reglement"))
    private Utilisateur utilisateur;

    @ManyToOne(targetEntity = Commande.class)
    @JoinColumn(name = "commande_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_commande_reglement"))
    private Commande commande;

    @ManyToOne(targetEntity = Caisse.class)
    @JoinColumn(name = "caisse_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_caisse_reglement"))
    private Caisse caisse;


    public static final class ReglementBuilder {
        private Long id;
        private Long montant;
        private Long rendu;
        private Long reste;
        private Utilisateur utilisateur;
        private Commande commande;
        private Caisse caisse;

        private ReglementBuilder() {
        }

        public static ReglementBuilder aReglement() {
            return new ReglementBuilder();
        }

        public ReglementBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ReglementBuilder montant(Long montant) {
            this.montant = montant;
            return this;
        }

        public ReglementBuilder rendu(Long rendu) {
            this.rendu = rendu;
            return this;
        }

        public ReglementBuilder reste(Long reste) {
            this.reste = reste;
            return this;
        }

        public ReglementBuilder utilisateur(Utilisateur utilisateur) {
            this.utilisateur = utilisateur;
            return this;
        }

        public ReglementBuilder commande(Commande commande) {
            this.commande = commande;
            return this;
        }

        public ReglementBuilder caisse(Caisse caisse) {
            this.caisse = caisse;
            return this;
        }


        public Reglement build() {
            Reglement reglement = new Reglement();
            reglement.setId(id);
            reglement.setMontant(montant);
            reglement.setRendu(rendu);
            reglement.setReste(reste);
            reglement.setUtilisateur(utilisateur);
            reglement.setCommande(commande);
            reglement.setCaisse(caisse);
            return reglement;
        }
    }
}

