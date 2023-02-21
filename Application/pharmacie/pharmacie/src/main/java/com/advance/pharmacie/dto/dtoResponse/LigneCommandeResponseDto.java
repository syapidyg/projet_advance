package com.advance.pharmacie.dto.dtoResponse;

import com.advance.pharmacie.model.Commande;
import com.advance.pharmacie.model.LigneCommande;
import com.advance.pharmacie.model.Produit;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LigneCommandeResponseDto {

    private Long id;
    private String status;
    private Long idCommande;
    private Long idProduit;

    public LigneCommande entityToDto(LigneCommandeResponseDto dto, Commande commande, Produit produit) {

//        CommandeRequestDto commandedto = new CommandeRequestDto();
//        ProduitRequestDto produitdto = new ProduitRequestDto();

        return LigneCommande.LigneCommandeBuilder.aLigneCommande()
                .id(dto.getId())
                .status(dto.getStatus())
                .commande(commande)
                .produit(produit)
                .build();


    }
}