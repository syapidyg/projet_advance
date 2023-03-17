package com.advance.pharmacie.dto.dtoRequest;

import com.advance.pharmacie.model.Famille;
import com.advance.pharmacie.model.Produit;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProduitRequestDto {

    private Long id;
    private String code;
    private String dci;
    private String forme;
    private String categorie;
    private String dosage;
    private Long pa;
    private Long pv;
    private Long idfamille;

    public static Produit dtoToEntity(ProduitRequestDto dto, Famille famille) {
        return Produit.ProduitBuilder.aProduit()
                .id(dto.getId())
                .code(dto.getCode())
                .dci(dto.getDci())
                .dosage(dto.getDosage())
                .forme(dto.getForme())
                .categorie(dto.getCategorie())
                .pa(dto.getPa())
                .pv(dto.getPv())
                .famille(famille)
                .build();
    }
}
