package com.advance.pharmacie.dto.dtoResponse;

import com.advance.pharmacie.model.Caisse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CaisseResponseDto {
    private Long id;
    private String name;

    public static CaisseResponseDto entityToDto(Caisse caisse) {
        return CaisseResponseDto.builder()
                .id(caisse.getId())
                .name(caisse.getName())
                .build();
    }
}
