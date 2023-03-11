package com.advance.pharmacie.dto.dtoRequest;


import com.advance.pharmacie.model.auth.Utilisateur;
import com.advance.pharmacie.util.GeneralUtil;
import lombok.Builder;
import lombok.Data;
import java.util.Date;

@Data
@Builder
public class UtilisateurRequestDto {

    private Long id;
    private String username;
    private String email;
    private String password;

    public static Utilisateur dtoToEntity(UtilisateurRequestDto dto) {
        return Utilisateur.UtilisateurBuilder.anUtilisateur()
                .id(dto.getId())
                .username(dto.getUsername())
                .password(GeneralUtil.genererPasswordUser(dto.getPassword()))
                .email(dto.getEmail())
                .build();

    }
}
