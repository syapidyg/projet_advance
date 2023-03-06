package com.advance.pharmacie.model;


import com.advance.pharmacie.model.lnk.Reglement;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "APP_CAISSE")
public class Caisse extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "caisse")
    private List<Reglement> reglements = new ArrayList<>();

    public static final class CaisseBuilder {
        private Long id;
        private String name;
        private String description;
        private List<Reglement> reglements;

        private CaisseBuilder() {
        }

        public static CaisseBuilder aCaisse() {
            return new CaisseBuilder();
        }

        public CaisseBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public CaisseBuilder name(String name) {
            this.name = name;
            return this;
        }

        public CaisseBuilder description(String description) {
            this.description = description;
            return this;
        }
        public CaisseBuilder reglements(List<Reglement> reglements) {
            this.reglements = reglements;
            return this;
        }

        public Caisse build() {
            Caisse caisse = new Caisse();
            caisse.setId(id);
            caisse.setName(name);
            caisse.setDescription(description);
            caisse.setReglements(reglements);
            return caisse;
        }
    }
}
