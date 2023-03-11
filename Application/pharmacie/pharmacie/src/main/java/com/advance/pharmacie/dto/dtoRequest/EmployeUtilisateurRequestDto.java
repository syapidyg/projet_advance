package com.advance.pharmacie.dto.dtoRequest;

import com.advance.pharmacie.model.Employe;
import com.advance.pharmacie.model.auth.Utilisateur;
import com.advance.pharmacie.util.GeneralUtil;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class EmployeUtilisateurRequestDto {

    private Long id;
    private String nom;
    private String prenom;
    private Date birthday;
    private String email;
    private Long number;
    private String username;
    private String password;


    public static Employe dtoToEntityEmploye(EmployeUtilisateurRequestDto dto) {
        return Employe.EmployeBuilder.anEmploye()
                .id(dto.getId())
                .nom(dto.getNom())
                .prenom(dto.getPrenom())
                .birthday(dto.getBirthday())
                .email(dto.getEmail())
                .number(dto.getNumber())
                .build();

    }
    public static Utilisateur dtoToEntityUtilisateur(EmployeUtilisateurRequestDto dto, Employe employe) {
        return Utilisateur.UtilisateurBuilder.anUtilisateur()
                .id(dto.getId())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(GeneralUtil.genererPasswordUser(dto.getPassword()))
                .employe(employe)
                .build();

    }
}
