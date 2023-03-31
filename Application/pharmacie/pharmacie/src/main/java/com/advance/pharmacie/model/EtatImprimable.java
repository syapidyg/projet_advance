package com.advance.pharmacie.model;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PRM_ETAT_IMPRIMABLE")

public class EtatImprimable extends AuditEntity {

    @Id
    @Column(name = "id_etat_imprimable")
    private Long id;

    @Column(name = "type_filtre")
    private Integer type; //  <0=Fenêtre, 1=Etat>

    @Column(nullable = false, length = 100)
    private String libelle;

    @Column(nullable = false, length = 100)
    private String libelle_en;

    @Column(nullable = false, length = 5000)
    private String description;

    @Column(nullable = false, length = 5000)
    private String description_en;
    private int numeroOrdre;

    @Column(length = 500)
    private String groupe;

    @Column(nullable = false, length = 5000)
    private String chemin;

    private int exportable; //  <0=Non, 1=Oui>

    /**
     * The Window.
     */
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
//    @JoinColumn(name = "window_id", referencedColumnName = "id")
//    private WindowApp window;
}
