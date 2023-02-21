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

    public static FamilleResponseDto entityToDto(Famille famille) {
        return FamilleResponseDto.builder()
                .id(famille.getId())
                .name(famille.getName())
                .description(famille.getDescription())
                .build();
    }


}
