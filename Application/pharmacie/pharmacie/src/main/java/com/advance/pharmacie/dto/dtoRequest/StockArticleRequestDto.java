package com.advance.pharmacie.dto.dtoRequest;

import com.advance.pharmacie.model.Depot;
import com.advance.pharmacie.model.Produit;
import com.advance.pharmacie.model.lnk.StockArticle;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StockArticleRequestDto {

    private Long id;
    private Long idDepot;
    private Long idProduit;
    private Long qte;
    private Long qteAlerte;
    private Long qteMinimale;
    private Long qteMaximale;


    public static StockArticle dtoToEntity(StockArticleRequestDto dto, Depot depot, Produit produit) {

        return StockArticle.StockArticleBuilder.aStockArticle()
                .id(dto.getId())
                .depot(depot)
                .produit(produit)
                .qte(dto.getQte())
                .qteAlerte(dto.getQteAlerte())
                .qteMaximale(dto.getQteMaximale())
                .qteMinimale(dto.getQteMinimale())
                .build();
    }

    public static StockArticle dtoToEntityFromFournisseur(Depot depot, Produit produit, Long qte, Long qteMAX, Long qteMIN, Long qteALERTE) {

        return StockArticle.StockArticleBuilder.aStockArticle()

                .depot(depot)
                .produit(produit)
                .qte(qte)
                .qteAlerte(qteALERTE)
                .qteMaximale(qteMAX)
                .qteMinimale(qteMIN)
                .build();
    }
}
