package com.advance.pharmacie.dto.dtoResponse;

import com.advance.pharmacie.model.Depot;
import com.advance.pharmacie.model.Produit;
import com.advance.pharmacie.model.StockArticle;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StockArticleResponseDto {

    private Long id;
    private Long idDepot;
    private Long idProduit;

    public StockArticle entityToDto(StockArticleResponseDto dto, Depot depot, Produit produit){

        return StockArticle.StockArticleBuilder.aStockArticle()
                        .id(dto.getId())
                        .depot(depot)
                        .produit(produit)
                        .build();
    }
}
