package com.advance.pharmacie.dto.dtoResponse;

import com.advance.pharmacie.model.lnk.LigneCommande;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LigneCommandeResponseDto {

    private Long id;
    private String status;
    private CommandeResponseDto commande;
    private ProduitResponseDto produit;

    public LigneCommandeResponseDto entityToDto(LigneCommande ligneCommande) {

//        CommandeRequestDto commandedto = new CommandeRequestDto();
//        ProduitRequestDto produitdto = new ProduitRequestDto();

        return LigneCommandeResponseDto.builder()
                .id(ligneCommande.getId())
                .status(ligneCommande.getStatus())
                .commande(CommandeResponseDto.entityToDto(ligneCommande.getCommande()))
                .produit(ProduitResponseDto.entityToDto(ligneCommande.getProduit()))
                .build();


    }
}
