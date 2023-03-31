package com.advance.pharmacie.model.auth;

import com.advance.pharmacie.model.Activite;
import com.advance.pharmacie.model.Employe;
import com.advance.pharmacie.model.AuditEntity;
import com.advance.pharmacie.model.Produit;
import com.advance.pharmacie.model.lnk.Reglement;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.NumberFormat;
import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.*;
@Entity
@Data
@Table(name = "AUTH_UTILISATEUR")
public class Utilisateur extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Email
    private String email;

    private String password;

    @ManyToOne(targetEntity = Role.class)
    @JoinColumn(name = "role_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_role_utilisateur"))
    private Role role;

    @OneToOne(targetEntity = Employe.class)
    @JoinColumn(name = "employe_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_employer_utilisateur"))
    private Employe employe;

//    @OneToMany(mappedBy = "utilisateur")
//    private List<Activite> activite = new ArrayList<>();


    @OneToMany(mappedBy = "utilisateur")
    private List<Reglement> reglements = new ArrayList<>();


    public static final class UtilisateurBuilder {
        private Long id;
        private String username;
        private @Email String email;
        private String password;
        private Role role;
        private Employe employe;

        private UtilisateurBuilder() {
        }

        public static UtilisateurBuilder anUtilisateur() {
            return new UtilisateurBuilder();
        }

        public UtilisateurBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UtilisateurBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UtilisateurBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UtilisateurBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UtilisateurBuilder role(Role role) {
            this.role = role;
            return this;
        }

        public UtilisateurBuilder employe(Employe employe) {
            this.employe = employe;
            return this;
        }

        public Utilisateur build() {
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setId(id);
            utilisateur.setUsername(username);
            utilisateur.setEmail(email);
            utilisateur.setPassword(password);
            utilisateur.setRole(role);
            utilisateur.setEmploye(employe);
            return utilisateur;
        }
    }
}