package com.advance.pharmacie.dto.dtoAuth;

import com.advance.pharmacie.dto.dtoResponse.UtilisateurResponseDto;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthenticationResponseDto {

    private String token;
    private UtilisateurResponseDto utilisateur;


    public static AuthenticationResponseDto entityToDto(UtilisateurResponseDto utilisateurResponse, String token) {
        return AuthenticationResponseDto.builder()
                .token(token)
                .utilisateur(utilisateurResponse)
                .build();

    }
}
