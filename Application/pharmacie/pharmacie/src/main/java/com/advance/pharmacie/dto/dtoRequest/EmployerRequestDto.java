package com.advance.pharmacie.dto.dtoRequest;

import com.advance.pharmacie.model.Employer;
import com.advance.pharmacie.model.Utilisateur;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.OneToOne;
import javax.validation.constraints.Email;

@Data
@Builder
public class EmployerRequestDto {

    private Long id;
    private String username;
    private String email;
    private Long number;
    private String password;
    private Long idUtillisateur;

    public Employer dtoToEntity(EmployerRequestDto dto, Utilisateur utilisateur) {
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
