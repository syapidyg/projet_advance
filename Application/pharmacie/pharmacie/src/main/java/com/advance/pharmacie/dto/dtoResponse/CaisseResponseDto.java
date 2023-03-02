package com.advance.pharmacie.dto.dtoResponse;

import com.advance.pharmacie.model.Caisse;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class CaisseResponseDto {
    private Long id;
    private String name;
    private String description;

    public static CaisseResponseDto entityToDto(Caisse caisse) {
        return CaisseResponseDto.builder()
                .id(caisse.getId())
                .name(caisse.getName())
                .description(caisse.getDescription())
                .build();
    }
    public static List<CaisseResponseDto> entityToDtoList(List<Caisse> caisseList){
        return caisseList.stream().map(CaisseResponseDto::entityToDto).collect(Collectors.toList());
    }

}
