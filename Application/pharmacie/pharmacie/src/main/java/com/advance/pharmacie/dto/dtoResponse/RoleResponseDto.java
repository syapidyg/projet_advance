package com.advance.pharmacie.dto.dtoResponse;


import com.advance.pharmacie.model.auth.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleResponseDto {

    private Long id;
    private String name;

    public RoleResponseDto entityToDto(Role role) {
        return RoleResponseDto.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }

}
