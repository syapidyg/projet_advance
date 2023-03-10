package com.advance.pharmacie.dto.dtoResponse;

import com.advance.pharmacie.model.Client;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

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

    public static List<ClientResponseDto> entityToDtoList(List<Client> clientList){
        return clientList.stream().map(ClientResponseDto::entityToDto).collect(Collectors.toList());
    }
}
