package com.advance.pharmacie.dto.dtoResponse;

import com.advance.pharmacie.model.Fournisseur;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FournisseurResponseDto {

    private Long id;
    private String email;
    private String name;
    private Long number;
    private String adress;

    public static FournisseurResponseDto entityToDto(Fournisseur fournisseur) {
        return FournisseurResponseDto.builder()
                .id(fournisseur.getId())
                .email(fournisseur.getEmail())
                .adress(fournisseur.getAdress())
                .name(fournisseur.getName())
                .number(fournisseur.getNumber())
                .build();
    }

}
