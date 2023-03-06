package com.advance.pharmacie.model;


import com.advance.pharmacie.model.auth.Utilisateur;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;

@Entity
@Data
@Table(name = "APP_EMPLOYER")
public class Employe extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;

    private Date birthday;


    @Email
    private String email;

    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private Long number;

    private String password;

    @OneToOne(targetEntity = Utilisateur.class)
    @JoinColumn(name = "utilisateur_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_employer_utilisateur"))
    private Utilisateur utilisateur;


    public static final class EmployerBuilder {
        private Long id;
        private String nom;
        private String prenom;
        private Date birthday;
        private @Email String email;
        private Long number;
        private String password;
        private Utilisateur utilisateur;

        private EmployerBuilder() {
        }

        public static EmployerBuilder anEmployer() {
            return new EmployerBuilder();
        }

        public EmployerBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public EmployerBuilder nom(String nom) {
            this.nom = nom;
            return this;
        }

        public EmployerBuilder prenom(String prenom) {
            this.prenom = prenom;
            return this;
        }

        public EmployerBuilder birthday(Date birthday) {
            this.birthday = birthday;
            return this;
        }

        public EmployerBuilder email(String email) {
            this.email = email;
            return this;
        }

        public EmployerBuilder number(Long number) {
            this.number = number;
            return this;
        }

        public EmployerBuilder password(String password) {
            this.password = password;
            return this;
        }

        public EmployerBuilder utilisateur(Utilisateur utilisateur) {
            this.utilisateur = utilisateur;
            return this;
        }

        public Employe build() {
            Employe employe = new Employe();
            employe.setId(id);
            employe.setNom(nom);
            employe.setPrenom(prenom);
            employe.setBirthday(birthday);
            employe.setEmail(email);
            employe.setNumber(number);
            employe.setPassword(password);
            employe.setUtilisateur(utilisateur);
            return employe;
        }
    }
}
