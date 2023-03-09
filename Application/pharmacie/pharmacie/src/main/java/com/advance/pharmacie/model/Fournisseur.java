package com.advance.pharmacie.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "APP_FOURNISSEUR")

public class Fournisseur extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    private String email;

    private String name;

    private Long number;

    private String adress;

    private String city;

    @OneToMany(mappedBy = "fournisseur")
    private List<Commande> commandes = new ArrayList<>();


    public static final class FournisseurBuilder {
        private Long id;
        private @Email String email;
        private String name;
        private Long number;
        private String adress;
        private String city;

        private FournisseurBuilder() {
        }

        public static FournisseurBuilder aFournisseur() {
            return new FournisseurBuilder();
        }

        public FournisseurBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public FournisseurBuilder email(String email) {
            this.email = email;
            return this;
        }

        public FournisseurBuilder name(String name) {
            this.name = name;
            return this;
        }

        public FournisseurBuilder number(Long number) {
            this.number = number;
            return this;
        }

        public FournisseurBuilder adress(String adress) {
            this.adress = adress;
            return this;
        }

        public FournisseurBuilder city(String city) {
            this.city = city;
            return this;
        }

        public Fournisseur build() {
            Fournisseur fournisseur = new Fournisseur();
            fournisseur.setId(id);
            fournisseur.setEmail(email);
            fournisseur.setName(name);
            fournisseur.setNumber(number);
            fournisseur.setAdress(adress);
            fournisseur.setCity(city);
            return fournisseur;
        }
    }
}

