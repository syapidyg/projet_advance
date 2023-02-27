package com.advance.pharmacie.dto.dtoRequest;

import com.advance.pharmacie.model.Caisse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CaisseRequestDto {
    private Long id;
    private String name;
    private String description;

    public static Caisse dtoToEntity(CaisseRequestDto dto) {
        return Caisse.CaisseBuilder.aCaisse()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
    }
}
