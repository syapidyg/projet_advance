package com.advance.pharmacie.dto.dtoResponse;

import com.advance.pharmacie.model.Client;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientResponseDto {

    private long id;
    private String name;
    private Long number;
    private String email;

    public Client entityToDto(ClientResponseDto dto) {
        return Client.ClientBuilder.aClient()
                .id(dto.getId())
                .name(dto.getName())
                .email(dto.getEmail())
                .number(dto.getNumber())
                .build();
    }

}
