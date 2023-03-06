package com.advance.pharmacie.dto.dtoResponse;

import com.advance.pharmacie.model.Caisse;
import com.advance.pharmacie.model.Famille;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class FamilleResponseDto {

    private Long id;
    private String name;
    private String rayon;
    private String description;

    public static FamilleResponseDto entityToDto(Famille famille) {
        return FamilleResponseDto.builder()
                .id(famille.getId())
                .name(famille.getName())
                .rayon(famille.getRayon())
                .description(famille.getDescription())
                .build();
    }


    public static List<FamilleResponseDto> entityToDtoList(List<Famille> familleList){
        return familleList.stream().map(FamilleResponseDto::entityToDto).collect(Collectors.toList());
    }
}
