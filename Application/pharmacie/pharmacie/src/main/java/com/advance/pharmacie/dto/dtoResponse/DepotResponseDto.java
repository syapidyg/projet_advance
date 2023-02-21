package com.advance.pharmacie.dto.dtoResponse;

import com.advance.pharmacie.model.Depot;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DepotResponseDto {

    private Long id;
    private String name;

    public static DepotResponseDto entityToDto(Depot depot) {
        return DepotResponseDto.builder()
                .id(depot.getId())
                .name(depot.getName())
                .build();
    }

}
