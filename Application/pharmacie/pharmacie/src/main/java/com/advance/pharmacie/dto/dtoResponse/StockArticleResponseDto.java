package com.advance.pharmacie.dto.dtoResponse;

import com.advance.pharmacie.model.Produit;
import com.advance.pharmacie.model.lnk.StockArticle;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class StockArticleResponseDto {

    private Long id;
    private Long qte;
    private Long qteAlerte;
    private Long qteMinimale;
    private Long qteMaximale;
    private DepotResponseDto depot;
    private ProduitResponseDto produit;

    public static StockArticleResponseDto entityToDto(StockArticle stockArticle) {

        return StockArticleResponseDto.builder()
                .id(stockArticle.getId())
                .qte(stockArticle.getQte())
                .qteAlerte(stockArticle.getQteAlerte())
                .qteMaximale(stockArticle.getQteMaximale())
                .qteMinimale(stockArticle.getQteMinimale())
                .depot(DepotResponseDto.entityToDto(stockArticle.getDepot()))
                .produit(ProduitResponseDto.entityToDto(stockArticle.getProduit()))
                .build();
    }

    public static List<StockArticleResponseDto> entityToDtoList(List<StockArticle> stockArticleList) {
        return stockArticleList.stream().map(StockArticleResponseDto::entityToDto).collect(Collectors.toList());
    }
}
