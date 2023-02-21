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
//    private List<Commande> commandes = new ArrayList<>();

    public Fournisseur entityToDto(FournisseurResponseDto dto) {
        return Fournisseur.FournisseurBuilder.aFournisseur()
                .id(dto.getId())
                .email(dto.getEmail())
                .adress(dto.getAdress())
                .name(dto.getName())
                .number(dto.getNumber())
//                .commandes(dto.getCommandes())
                .build();
    }

}
