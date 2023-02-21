package com.advance.pharmacie.dto.dtoResponse;


import com.advance.pharmacie.model.Utilisateur;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UtilisateurResponseDto {

    private Long id;
    private String username;
    private String email;
    private Long number;
    private String password;


    public Utilisateur entityToDto(UtilisateurResponseDto dto) {
        return Utilisateur.UtilisateurBuilder.anUtilisateur()
                .id(dto.getId())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .number(dto.getNumber())
                .build();

    }
}
