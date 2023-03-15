package com.advance.pharmacie.dto.dtoResponse;

import com.advance.pharmacie.model.Famille;
import com.advance.pharmacie.model.Fournisseur;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class FournisseurResponseDto {

    private Long id;
    private String email;
    private String name;
    private Long number;
    private String adress;
    private String city;

    public static FournisseurResponseDto entityToDto(Fournisseur fournisseur) {
        if (fournisseur != null) {
            return FournisseurResponseDto.builder()
                    .id(fournisseur.getId())
                    .email(fournisseur.getEmail())
                    .adress(fournisseur.getAdress())
                    .name(fournisseur.getName())
                    .number(fournisseur.getNumber())
                    .city(fournisseur.getCity())
                    .build();
        }
        return null;
    }

    public static List<FournisseurResponseDto> entityToDtoList(List<Fournisseur> fournisseurList) {
        return fournisseurList.stream().map(FournisseurResponseDto::entityToDto).collect(Collectors.toList());
    }

}
