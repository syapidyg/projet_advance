package com.advance.pharmacie.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

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

    private String rayon;

    private String description;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ToString.Exclude
    @OneToMany(mappedBy = "famille")
    private List<Produit> produits = new ArrayList<>();


    public static final class FamilleBuilder {
        private Long id;
        private String name;
        private String rayon;
        private String description;

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

        public FamilleBuilder rayon(String rayon) {
            this.rayon = rayon;
            return this;
        }

        public FamilleBuilder description(String description) {
            this.description = description;
            return this;
        }

        public Famille build() {
            Famille famille = new Famille();
            famille.setId(id);
            famille.setName(name);
            famille.setRayon(rayon);
            famille.setDescription(description);
            return famille;
        }
    }
}
