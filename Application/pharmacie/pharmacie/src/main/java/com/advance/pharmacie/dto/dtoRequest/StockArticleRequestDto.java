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

    public StockArticle dtoToEntity(StockArticleRequestDto dto, Depot depot, Produit produit){

        return StockArticle.StockArticleBuilder.aStockArticle()
                        .id(dto.getId())
                        .depot(depot)
                        .produit(produit)
                        .qte(dto.getQte())
                        .qteAlerte(dto.getQteAlerte())
                        .build();
    }
}
