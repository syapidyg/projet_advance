package com.advance.pharmacie.model.sys;

import com.advance.pharmacie.model.AuditEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sys_fenetre")
public class Fenetre extends AuditEntity implements Comparable<Fenetre> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fenetre")
    private Long id;

    @Column(name = "id_objet")
    private String idObjet;

    @Column(name = "libelle_fenetre", nullable = false, length = 100)
    private String libelleFenetre;

    @Column(nullable = false, length = 5000)
    private String description;

    @Column(nullable = false, length = 500)
    private String url;

    @Column(name = "fenetre_editable")
    private int fenetreEditable; // <0=Non, 1=Oui>

    @Column(name = "fenetre_filtre")
    private int fenetreFiltre; // <0=Non, 1=Oui>

    @Column(name = "fenetre_impression")
    private int fenetreImpression; // <0=Non, 1=Oui>
    @Column(name = "autorise_etat")
    private int autoriseEtat; // <0=Non, 1=Oui>

    @JoinColumn(name = "id_module")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ManyToOne(targetEntity = ModuleSysteme.class)
    private ModuleSysteme module;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Fenetre fenetre = (Fenetre) o;
        return Objects.equals(id, fenetre.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }


    @Override
    public int compareTo(@NotNull Fenetre o) {
        return id.compareTo(o.id);
    }
}
