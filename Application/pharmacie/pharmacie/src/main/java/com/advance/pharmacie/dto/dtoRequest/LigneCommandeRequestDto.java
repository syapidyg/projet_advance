package com.advance.pharmacie.dto.dtoRequest;

import com.advance.pharmacie.model.Commande;
import com.advance.pharmacie.model.lnk.LigneCommande;
import com.advance.pharmacie.model.Produit;
import lombok.Builder;
import lombok.Data;

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
                .id(dto.getId())
                .pt(dto.getPt())
                .qte(dto.getQte())
                .commande(commande)
                .produit(produit)
                .build();


    }
}
