package com.advance.pharmacie.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PRM_NUMEROTATION")
public class Numerotation extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_numerotation")
    private Long id;

    @Column(name = "souche", length = 500)
    private String souche;

    @Column(name = "num_index")
    private long numeroIndex;

    @Column(name = "code", length = 50)
    private String code;

    @Column(name = "description", length = 500)
    private String description;
}
