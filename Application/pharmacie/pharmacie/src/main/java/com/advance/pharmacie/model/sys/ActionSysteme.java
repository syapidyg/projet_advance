package com.advance.pharmacie.model.sys;

import com.advance.pharmacie.model.AuditEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sys_action")
public class ActionSysteme extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_action")
    private Long id;

    @Column(name = "id_objet")
    private String idObjet;

    @Column(name = "libelle_action", nullable = false, length = 100)
    private String libelleAction;

    @Column(nullable = false, length = 5000)
    private String description;

    @Column(name = "type_objet")
    private int typeObjet; // <0=Menu, 1=Item_Menu, 2=Bouton, 3=Zone_de_saisie, 4=Zone_de_liste>, 5=Etat_imprimable>

    @JoinColumn(name="id_fenetre")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ManyToOne (targetEntity = Fenetre.class)
    private Fenetre fenetre;
}

