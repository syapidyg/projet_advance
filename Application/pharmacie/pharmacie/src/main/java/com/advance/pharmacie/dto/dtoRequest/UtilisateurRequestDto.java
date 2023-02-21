package com.advance.pharmacie.dto.dtoRequest;


import com.advance.pharmacie.model.Utilisateur;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import java.util.Date;

@Data
@Builder
public class UtilisateurRequestDto {

    private Long id;
    private String nom;
    private String prenom;
    private Date birthday;
    private String email;
    private Long number;
    private String password;

    public Utilisateur dtoToEntity(UtilisateurRequestDto dto) {
        return Utilisateur.UtilisateurBuilder.anUtilisateur()
                .id(dto.getId())
                .nom(dto.getNom())
                .prenom(dto.getPrenom())
                .birthday(dto.getBirthday())
                .email(dto.getEmail())
                .number(dto.getNumber())
                .build();

    }
}
