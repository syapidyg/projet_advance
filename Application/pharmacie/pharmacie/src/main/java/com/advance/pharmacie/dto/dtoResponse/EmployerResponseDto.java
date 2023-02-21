package com.advance.pharmacie.dto.dtoResponse;

import com.advance.pharmacie.model.Employer;
import com.advance.pharmacie.model.Utilisateur;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployerResponseDto {

    private Long id;
    private String username;
    private String email;
    private Long number;
    private String password;
    private Long idUtillisateur;

    public Employer entityToDto(EmployerResponseDto dto, Utilisateur utilisateur) {
        return Employer.EmployerBuilder.anEmployer()
                .id(dto.getId())
                .email(dto.getEmail())
                .username(dto.getUsername())
                .number(dto.getNumber())
                .password(dto.getPassword())
                .utilisateur(utilisateur)
                .build();
    }
}
