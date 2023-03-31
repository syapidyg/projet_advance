package com.advance.pharmacie.dto.dtoResponse;

import com.advance.pharmacie.model.Activite;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

@Data
@Builder
public class ActiviteResponseDto {
    private LocalDateTime date;
    private String details;
    private String nomUtilisateur;

    public static ActiviteResponseDto entityToDto(Activite activite) {
        return ActiviteResponseDto.builder()
                .date(activite.getDateEvenement())
                .details(activite.getDetails())
                .nomUtilisateur(activite.getNomUtilisateur())
                .build();
    }

    public static Page<ActiviteResponseDto> entityToDtoList(Page<Activite> activites){
        return activites.map(ActiviteResponseDto::entityToDto);
    }

}
