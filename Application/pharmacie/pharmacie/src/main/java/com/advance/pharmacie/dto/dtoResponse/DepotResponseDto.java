package com.advance.pharmacie.dto.dtoResponse;

import com.advance.pharmacie.model.Depot;
import com.advance.pharmacie.model.Famille;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
public class DepotResponseDto {

    private Long id;
    private String name;
    private String description;

    public static DepotResponseDto entityToDto(Depot depot) {
        return DepotResponseDto.builder()
                .id(depot.getId())
                .name(depot.getName())
                .description(depot.getDescription())
                .build();
    }

    public static List<DepotResponseDto> entityToDtoList(List<Depot> depotList){
        return depotList.stream().map(DepotResponseDto::entityToDto).collect(Collectors.toList());
    }

}
