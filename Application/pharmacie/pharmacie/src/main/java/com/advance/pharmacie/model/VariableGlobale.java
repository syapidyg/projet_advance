package com.advance.pharmacie.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "prm_variable_globale")

public class VariableGlobale extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_variable_globale")
    private Long id;

    @Column(name = "cle", nullable = false, length = 100)
    private String cle;

    @Column(name = "valeur", nullable = false, length = 5000)
    private String valeur;

    @Column(name = "description", length = 5000)
    private String description;

    @Column(name = "visible")
    private int visible; //  <0=Non, 1=Oui>

    @Column(name = "type_donnees")
    private int typeDonnees; //   (0=Chaine, 1=Numérique, 2=Booleen, 3=Date, 4=Timestamp)

    @Override
    public String toString() {
        return "VariableGlobale{" +
                "id=" + id +
                ", cle='" + cle + '\'' +
                ", valeur='" + valeur + '\'' +
                ", description='" + description + '\'' +
                ", visible=" + visible +
                ", typeDonnees=" + typeDonnees +
                '}';
    }
}
