package com.advance.pharmacie.dto.dtoResponse;

import com.advance.pharmacie.model.lnk.LigneCommande;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class LigneCommandeResponseDto {

    private Long id;
    private String status;
    private Long qte;
    private CommandeResponseDto commande;
    private ProduitResponseDto produit;

    public static LigneCommandeResponseDto entityToDto(LigneCommande ligneCommande) {

//        CommandeRequestDto commandedto = new CommandeRequestDto();
//        ProduitRequestDto produitdto = new ProduitRequestDto();

        return LigneCommandeResponseDto.builder()
                .id(ligneCommande.getId())
                .status(ligneCommande.getStatus())
                .qte(ligneCommande.getQte())
                .commande(CommandeResponseDto.entityToDto(ligneCommande.getCommande()))
                .produit(ProduitResponseDto.entityToDto(ligneCommande.getProduit()))
                .build();


    }

    public static List<LigneCommandeResponseDto> entityToDtoList(List<LigneCommande> ligneCommandes) {
        return ligneCommandes.stream().map(LigneCommandeResponseDto::entityToDto).collect(Collectors.toList());

    }
}
