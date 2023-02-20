package com.advance.pharmacie.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "APP_FOURNISSEUR")

public class Fournisseur extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    private String email;

    private String name;

    private Long number;

    private String adress;

    @OneToMany(mappedBy = "fournisseur")
    private List<Commande> commandes = new ArrayList<>();

}

