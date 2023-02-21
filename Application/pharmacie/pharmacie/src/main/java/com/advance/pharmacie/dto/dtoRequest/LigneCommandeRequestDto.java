package com.advance.pharmacie.dto.dtoRequest;

import com.advance.pharmacie.model.Commande;
import com.advance.pharmacie.model.LigneCommande;
import com.advance.pharmacie.model.Produit;
import lombok.Builder;
import lombok.Data;

import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Builder
public class LigneCommandeRequestDto {

    private Long id;
    private String status;
    private Long idCommande;
    private Long idProduit;

    public LigneCommande dtoToEntity(LigneCommandeRequestDto dto, Commande commande, Produit produit) {

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
