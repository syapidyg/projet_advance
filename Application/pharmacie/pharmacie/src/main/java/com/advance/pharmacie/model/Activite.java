package com.advance.pharmacie.model;

import com.advance.pharmacie.model.auth.Utilisateur;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "log_activite")
public class Activite extends AuditEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_activite")
    private Long id;

    @Column(name = "date_evt", nullable = false)
    private LocalDateTime dateEvenement = LocalDateTime.now();

    @Column(name = "details_evt", nullable = false, length = 5000)
    private String details;

    @Column(name = "nom_utilisateur", nullable = false, length = 500)
    private String nomUtilisateur = "SYSTEM";

//    @Column(name = "libelle_role", nullable = true)
//    private String libelleRole = "SYSTEM";

//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
//    @ManyToOne(targetEntity = Utilisateur.class)
//    @JoinColumn(name ="id_utilisateur", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_Activite_Utilisateur"))
//    private Utilisateur utilisateur;

    @Lob
    @Column(name = "parametres")
    private String parametres;

    @Column(name = "source_evt", length = 5000)
    private String source;

    @Column(name = "exception_erreur",nullable = true, length = 1000)
    private String erreur;


}
