package com.advance.pharmacie.dto.dtoResponse;

import com.advance.pharmacie.model.Caisse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CaisseResponseDto {
    private Long id;
    private String name;

    public Caisse entityToDto(CaisseResponseDto dtoResponse) {
        return Caisse.CaisseBuilder.aCaisse()
                .id(dtoResponse.getId())
                .name(dtoResponse.getName())
                .build();
    }
}
