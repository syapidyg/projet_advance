package com.advance.pharmacie.dto.dtoResponse;

import com.advance.pharmacie.model.lnk.StockArticle;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StockArticleResponseDto {

    private Long id;
    private DepotResponseDto depot;
    private ProduitResponseDto produit;

    public StockArticleResponseDto entityToDto(StockArticle stockArticle){

        return StockArticleResponseDto.builder()
                        .id(stockArticle.getId())
                        .depot(DepotResponseDto.entityToDto(stockArticle.getDepot()))
                        .produit(ProduitResponseDto.entityToDto(stockArticle.getProduit()))
                        .build();
    }
}
