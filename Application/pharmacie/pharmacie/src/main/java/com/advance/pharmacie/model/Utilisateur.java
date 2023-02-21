package com.advance.pharmacie.model;

import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private Long number;

    private String password;

    @ManyToOne(targetEntity = Role.class)
    @JoinColumn(name = "role_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_role_utilisateur"))
    private Role role;

    @OneToOne(mappedBy = "utilisateur")
    private Employer employer = new Employer();

    @OneToMany(mappedBy = "utilisateur")
    private List<Reglement> reglements = new ArrayList<>();


    public static final class UtilisateurBuilder {
        private Long id;
        private String username;
        private @Email String email;
        private Long number;
        private String password;
        private Role role;

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

        public UtilisateurBuilder number(Long number) {
            this.number = number;
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

        public Utilisateur build() {
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setId(id);
            utilisateur.setUsername(username);
            utilisateur.setEmail(email);
            utilisateur.setNumber(number);
            utilisateur.setPassword(password);
            utilisateur.setRole(role);
            return utilisateur;
        }
    }
}
