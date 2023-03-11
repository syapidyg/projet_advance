package com.advance.pharmacie.dto.dtoResponse;


import com.advance.pharmacie.model.auth.Utilisateur;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class UtilisateurResponseDto {

    private Long id;
    private String username;
    private String email;
    private EmployeResponseDto employe;


    public static UtilisateurResponseDto entityToDto(Utilisateur utilisateur) {
        return UtilisateurResponseDto.builder()
                .id(utilisateur.getId())
                .username(utilisateur.getUsername())
                .email(utilisateur.getEmail())
                .employe(EmployeResponseDto.entityToDto(utilisateur.getEmploye()))
                .build();

    }
}
