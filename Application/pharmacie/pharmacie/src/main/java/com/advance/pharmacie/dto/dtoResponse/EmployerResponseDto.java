package com.advance.pharmacie.dto.dtoResponse;

import com.advance.pharmacie.model.Employer;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class EmployerResponseDto {

    private Long id;
    private String nom;
    private String prenom;
    private Date birthday;
    private String email;
    private Long number;
    private UtilisateurResponseDto utilisateur;

    public static EmployerResponseDto entityToDto(Employer employer) {
        return EmployerResponseDto.builder()
                .id(employer.getId())
                .nom(employer.getNom())
                .prenom(employer.getPrenom())
                .birthday(employer.getBirthday())
                .email(employer.getEmail())
                .number(employer.getNumber())
                .utilisateur(UtilisateurResponseDto.entityToDto(employer.getUtilisateur()))
                .build();
    }
}
