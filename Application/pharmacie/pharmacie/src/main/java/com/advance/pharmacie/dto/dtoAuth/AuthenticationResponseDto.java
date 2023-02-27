package com.advance.pharmacie.dto.dtoAuth;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthenticationResponseDto {

    private String token;
}
