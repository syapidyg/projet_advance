package com.advance.pharmacie.dto.dtoRequest;

import com.advance.pharmacie.dto.dtoResponse.ClientResponseDto;
import com.advance.pharmacie.model.Client;
import com.advance.pharmacie.model.Commande;
import com.advance.pharmacie.model.lnk.LigneCommande;
import com.advance.pharmacie.model.Produit;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class LigneCommandeRequestDto {

    private Long id;
    private Long pt;
    private Long qte;
    private Long idCommande;
    private Long idProduit;

    public static LigneCommande dtoToEntity(LigneCommandeRequestDto dto, Commande commande, Produit produit) {

//        CommandeRequestDto commandedto = new CommandeRequestDto();
//        ProduitRequestDto produitdto = new ProduitRequestDto();

        return LigneCommande.LigneCommandeBuilder.aLigneCommande()
//                .id(dto.getId())
                .pt(dto.getPt())
                .qte(dto.getQte())
                .commande(commande)
                .produit(produit)
                .build();


    }

    public static LigneCommandeRequestDto entityToDto(LigneCommande ligneCommande) {

//        CommandeRequestDto commandedto = new CommandeRequestDto();
//        ProduitRequestDto produitdto = new ProduitRequestDto();

        return LigneCommandeRequestDto.builder()
                .id(ligneCommande.getId())
                .pt(ligneCommande.getPt())
                .qte(ligneCommande.getQte())
                .idCommande(ligneCommande.getCommande().getId())
                .idProduit(ligneCommande.getProduit().getId())
                .build();


    }

    public static LigneCommandeRequestDto entityToDtoT(LigneCommande ligneCommande) {

//        CommandeRequestDto commandedto = new CommandeRequestDto();
//        ProduitRequestDto produitdto = new ProduitRequestDto();

        return LigneCommandeRequestDto.builder()
//                .id(ligneCommande.getId())
                .pt(ligneCommande.getPt())
                .qte(ligneCommande.getQte())
                .idProduit(ligneCommande.getProduit().getId())
                .build();


    }

    public static List<LigneCommandeRequestDto> entityToDtoList(List<LigneCommande> ligneCommandeList) {
        return ligneCommandeList.stream().map(LigneCommandeRequestDto::entityToDto).collect(Collectors.toList());
    }

    public static List<LigneCommandeRequestDto> entityToDtoListT(List<LigneCommande> ligneCommandeList) {
        return ligneCommandeList.stream().map(LigneCommandeRequestDto::entityToDtoT).collect(Collectors.toList());
    }
}
