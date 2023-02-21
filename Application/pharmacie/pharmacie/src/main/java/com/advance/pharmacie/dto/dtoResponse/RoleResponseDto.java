package com.advance.pharmacie.dto.dtoResponse;


import com.advance.pharmacie.model.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleResponseDto {

    private Long id;
    private String name;

    public Role entityToDto(RoleResponseDto dto) {
        return Role.RoleBuilder.aRole()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }

}
