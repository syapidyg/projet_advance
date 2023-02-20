package com.advance.pharmacie.model;


import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "APP_CAISSE")
public class Caisse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "REGLEMENT",
            joinColumns = @JoinColumn(name = "caisse_id"),
            inverseJoinColumns = @JoinColumn(name = "utilisateur_id")

    )
    private Set<Utilisateur> utilisateurs = new HashSet<>();
}
