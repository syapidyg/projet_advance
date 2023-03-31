package com.advance.pharmacie.dto.dtoResponse;

import com.advance.pharmacie.model.Client;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

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
        if (client != null) {
            return ClientResponseDto.builder()
                    .id(client.getId())
                    .name(client.getName())
                    .number(client.getNumber())
                    .email(client.getEmail())
                    .build();
        }
        return null;
    }

    public static List<ClientResponseDto> entityToDtoList(List<Client> clientList){
        return clientList.stream().map(ClientResponseDto::entityToDto).collect(Collectors.toList());
    }
    public static Page<ClientResponseDto> entityToDtoList(Page<Client> clientList){
        return clientList.map(ClientResponseDto::entityToDto);
    }
}
