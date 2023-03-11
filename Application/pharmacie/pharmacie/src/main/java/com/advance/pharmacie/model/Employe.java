package com.advance.pharmacie.model;


import com.advance.pharmacie.model.auth.Utilisateur;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;

@Entity
@Data
@Table(name = "APP_EMPLOYE")
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

    @OneToOne(mappedBy = "employe")
    private Utilisateur utilisateur = new Utilisateur();

    public static final class EmployeBuilder {
        private Long id;
        private String nom;
        private String prenom;
        private Date birthday;
        private @Email String email;
        private Long number;
        private Utilisateur utilisateur;

        private EmployeBuilder() {
        }

        public static EmployeBuilder anEmploye() {
            return new EmployeBuilder();
        }

        public EmployeBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public EmployeBuilder nom(String nom) {
            this.nom = nom;
            return this;
        }

        public EmployeBuilder prenom(String prenom) {
            this.prenom = prenom;
            return this;
        }

        public EmployeBuilder birthday(Date birthday) {
            this.birthday = birthday;
            return this;
        }

        public EmployeBuilder email(String email) {
            this.email = email;
            return this;
        }

        public EmployeBuilder number(Long number) {
            this.number = number;
            return this;
        }

        public EmployeBuilder utilisateur(Utilisateur utilisateur) {
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
            employe.setUtilisateur(utilisateur);
            return employe;
        }
    }
}
