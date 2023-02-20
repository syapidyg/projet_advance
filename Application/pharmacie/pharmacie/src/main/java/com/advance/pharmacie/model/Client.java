package com.advance.pharmacie.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "APP_CLIENT")
public class Client extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private Long number;

    @Email
    private String email;

    @OneToMany(mappedBy = "client")
    private List<Commande> commandes = new ArrayList<>();


}
