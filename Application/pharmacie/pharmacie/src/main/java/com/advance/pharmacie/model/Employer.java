package com.advance.pharmacie.model;


import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;

@Entity
@Data
@Table(name = "APP_EMPLOYER")
public class Employer extends AuditEntity {

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

        public Employer build() {
            Employer employer = new Employer();
            employer.setId(id);
            employer.setNom(nom);
            employer.setPrenom(prenom);
            employer.setBirthday(birthday);
            employer.setEmail(email);
            employer.setNumber(number);
            employer.setPassword(password);
            employer.setUtilisateur(utilisateur);
            return employer;
        }
    }
}
