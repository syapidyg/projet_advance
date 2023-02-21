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

    public static ClientResponseDto entityToDto(Client client) {
        return ClientResponseDto.builder()
                .id(client.getId())
                .name(client.getName())
                .number(client.getNumber())
                .email(client.getEmail())
                .build();
    }

}
