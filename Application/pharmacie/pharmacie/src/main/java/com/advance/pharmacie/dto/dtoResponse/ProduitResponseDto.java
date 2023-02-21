package com.advance.pharmacie.dto.dtoResponse;

import com.advance.pharmacie.model.Famille;
import com.advance.pharmacie.model.Produit;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProduitResponseDto {

    private Long id;
    private String dci;
    private String forme;
    private String dosage;
    private String rayon;
    private Long pa;
    private Long pv;
    private Long idfamille;

    public Produit dtoToEntity(ProduitResponseDto dto, Famille famille) {
        return Produit.ProduitBuilder.aProduit()
                .id(dto.getId())
                .dci(dto.getDci())
                .dosage(dto.getDosage())
                .forme(dto.getForme())
                .rayon(dto.getRayon())
                .pa(dto.getPa())
                .pv(dto.getPv())
                .famille(famille)
                .build();
    }
}
