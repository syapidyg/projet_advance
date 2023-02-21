package com.advance.pharmacie.dto.dtoResponse;

import com.advance.pharmacie.model.Famille;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FamilleResponseDto {

    private Long id;
    private String name;
    private String description;

    public Famille entityToDto(FamilleResponseDto dto) {
        return Famille.FamilleBuilder.aFamille()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
    }


}
