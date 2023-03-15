package com.advance.pharmacie.dto.dtoResponse;

import com.advance.pharmacie.model.lnk.LigneCommande;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class LigneCommandeResponseDto {

    private Long id;
    private Long qte;
    private Long idCommande;
    private Long pt;
    private ProduitResponseDto produit;

    public static LigneCommandeResponseDto entityToDto(LigneCommande ligneCommande) {

//        CommandeRequestDto commandedto = new CommandeRequestDto();
//        ProduitRequestDto produitdto = new ProduitRequestDto();

        return LigneCommandeResponseDto.builder()
                .id(ligneCommande.getId())
                .pt(ligneCommande.getPt())
                .qte(ligneCommande.getQte())
                .idCommande(ligneCommande.getCommande().getId())
                .produit(ProduitResponseDto.entityToDto(ligneCommande.getProduit()))
                .build();


    }

    public static List<LigneCommandeResponseDto> entityToDtoList(List<LigneCommande> ligneCommandes) {

        return ligneCommandes.isEmpty() ? new ArrayList<>(): ligneCommandes.stream().map(LigneCommandeResponseDto::entityToDto).collect(Collectors.toList());

    }
}
