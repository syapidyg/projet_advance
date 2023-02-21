package com.advance.pharmacie.model;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "APP_FAMILLE")
public class Famille extends AuditEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "famille")
    private List<Produit> produits = new ArrayList<>();

    public static final class FamilleBuilder {
        private Long id;
        private String name;
        private String description;
        private List<Produit> produits;

        private FamilleBuilder() {
        }

        public static FamilleBuilder aFamille() {
            return new FamilleBuilder();
        }

        public FamilleBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public FamilleBuilder name(String name) {
            this.name = name;
            return this;
        }

        public FamilleBuilder description(String description) {
            this.description = description;
            return this;
        }

        public FamilleBuilder produits(List<Produit> produits) {
            this.produits = produits;
            return this;
        }

        public Famille build() {
            Famille famille = new Famille();
            famille.setId(id);
            famille.setName(name);
            famille.setDescription(description);
            famille.setProduits(produits);
            return famille;
        }
    }
}
