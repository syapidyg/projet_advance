package com.advance.pharmacie.dto.dtoResponse;


import com.advance.pharmacie.model.Utilisateur;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class UtilisateurResponseDto {

    private String resultat;
    private Long id;
    private String nom;
    private String prenom;
    private Date birthday;
    private String email;
    private Long number;


    public static UtilisateurResponseDto entityToDto(Utilisateur utilisateur) {
        return UtilisateurResponseDto.builder()
                .id(utilisateur.getId())
                .nom(utilisateur.getNom())
                .prenom(utilisateur.getPrenom())
                .birthday(utilisateur.getBirthday())
                .email(utilisateur.getEmail())
                .number(utilisateur.getNumber())
                .build();

    }
}
