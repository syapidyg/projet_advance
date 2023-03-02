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
    private String nom;
    private String prenom;
    private String username;
    private Date birthday;
    private String email;
    private Long number;
    private String password;

    public static Utilisateur dtoToEntity(UtilisateurRequestDto dto) {
        return Utilisateur.UtilisateurBuilder.anUtilisateur()
                .id(dto.getId())
                .nom(dto.getNom())
                .prenom(dto.getPrenom())
                .username(dto.getUsername())
                .birthday(dto.getBirthday())
                .password(GeneralUtil.genererPasswordUser(dto.getPassword()))
                .email(dto.getEmail())
                .number(dto.getNumber())
                .build();

    }
}
