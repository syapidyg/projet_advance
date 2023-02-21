package com.advance.pharmacie.dto.dtoRequest;

import com.advance.pharmacie.model.Famille;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FamilleRequestDto {

    private Long id;
    private String name;
    private String description;

    public Famille dtoToEntity(FamilleRequestDto dto) {
        return Famille.FamilleBuilder.aFamille()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
    }


}
