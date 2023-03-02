package com.advance.pharmacie.dto.dtoRequest;


import com.advance.pharmacie.model.auth.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleRequestDto {

    private Long id;
    private String name;

    public Role dtoToEntity(RoleRequestDto dto) {
        return Role.RoleBuilder.aRole()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }

}
