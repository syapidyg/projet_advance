package com.advance.pharmacie.dto.dtoRequest;

import com.advance.pharmacie.model.Depot;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DepotRequestDto {

    private Long id;
    private String name;
    private String description;

    public static Depot dtoToEntity(DepotRequestDto dto) {
        return Depot.DepotBuilder.aDepot()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
    }

}
