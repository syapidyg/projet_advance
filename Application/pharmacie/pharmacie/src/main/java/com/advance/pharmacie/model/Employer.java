package com.advance.pharmacie.model;


import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Data
@Table(name = "APP_EMPLOYER")
public class Employer extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Email
    private String email;

    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private Long number;

    private String password;

    @OneToOne(mappedBy = "employer")
    private Utilisateur utilisateur = new Utilisateur();

}
