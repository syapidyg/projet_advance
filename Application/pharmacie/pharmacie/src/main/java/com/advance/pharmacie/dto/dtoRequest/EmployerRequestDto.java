package com.advance.pharmacie.dto.dtoRequest;

import com.advance.pharmacie.model.Employer;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class EmployerRequestDto {

    private Long id;
    private String nom;
    private String prenom;
    private Date birthday;
    private String email;
    private Long number;
    private String password;
    private Long idUtilisateur;

    public static Employer dtoToEntity(EmployerRequestDto dto) {
        return Employer.EmployerBuilder.anEmployer()
                .id(dto.getId())
                .nom(dto.getNom())
                .prenom(dto.getPrenom())
                .birthday(dto.getBirthday())
                .email(dto.getEmail())
                .number(dto.getNumber())
                .build();

    }
}
