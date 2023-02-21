package com.advance.pharmacie.dto.dtoRequest;

import com.advance.pharmacie.model.Client;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Email;

@Data
@Builder
public class ClientRequestDto {

    private long id;
    private String name;
    private Long number;
    private String email;

    public static Client dtoToEntity(ClientRequestDto dto) {
        return Client.ClientBuilder.aClient()
                .id(dto.getId())
                .name(dto.getName())
                .email(dto.getEmail())
                .number(dto.getNumber())
                .build();
    }

}
