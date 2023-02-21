package com.advance.pharmacie.dto.dtoRequest;

import com.advance.pharmacie.model.Famille;
import com.advance.pharmacie.model.Produit;
import lombok.Builder;
import lombok.Data;

import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Builder
public class ProduitRequestDto {

    private Long id;
    private String dci;
    private String forme;
    private String dosage;
    private String rayon;
    private Long pa;
    private Long pv;
    private Long idfamille;

    public Produit dtoToEntity(ProduitRequestDto dto, Famille famille) {
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