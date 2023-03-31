package com.advance.pharmacie.dto.dtoResponse;


import com.advance.pharmacie.model.Produit;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class ProduitResponseDto {

    private Long id;
    private String dci;
    private String code;
    private String forme;
    private String categorie;
    private String dosage;
    private String rayon;
    private Long pa;
    private Long pv;
    private FamilleResponseDto famille;

    public static ProduitResponseDto entityToDto(Produit produit) {
        return ProduitResponseDto.builder()
                .id(produit.getId())
                .code(produit.getCode())
                .dci(produit.getDci())
                .dosage(produit.getDosage())
                .forme(produit.getForme())
                .categorie(produit.getCategorie())
                .pa(produit.getPa())
                .pv(produit.getPv())
                .famille(FamilleResponseDto.entityToDto(produit.getFamille()))
                .build();
    }

    public static List<ProduitResponseDto> entityToDtoList(List<Produit> produitList){
        return produitList.stream().map(ProduitResponseDto::entityToDto).collect(Collectors.toList());
    }

    public static Page<ProduitResponseDto> entityToDtoList(Page<Produit> produitList){
        return produitList.map(ProduitResponseDto::entityToDto);
    }
}
