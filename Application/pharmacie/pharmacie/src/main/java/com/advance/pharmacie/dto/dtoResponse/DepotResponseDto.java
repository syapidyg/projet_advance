package com.advance.pharmacie.dto.dtoResponse;

import com.advance.pharmacie.model.Depot;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DepotResponseDto {

    private Long id;
    private String name;

    public Depot entityToDto(DepotResponseDto dto) {
        return Depot.DepotBuilder.aDepot()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }

}
