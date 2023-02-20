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

    @OneToOne(targetEntity = Employer.class)
    @JoinColumn(name = "employer_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_employer_utilisateur"))
    private Employer employer;

    @OneToMany(mappedBy = "utilisateur")
    private List<Reglement> reglements = new ArrayList<>();



}
