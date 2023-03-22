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
    private String statut;
    private Long qteMaximale;
    private DepotResponseDto depot;
    private ProduitResponseDto produit;

    public static StockArticleResponseDto entityToDto(StockArticle stockArticle) {

        String statutSave;
        if (stockArticle.getQte()> stockArticle.getQteMaximale()) {
            statutSave="En excès";
        } else if (stockArticle.getQte() >= stockArticle.getQteAlerte() && stockArticle.getQte() <= stockArticle.getQteMaximale()){
            statutSave="Normal";
        }else if (stockArticle.getQte() < stockArticle.getQteAlerte() && stockArticle.getQte() > stockArticle.getQteMinimale()){
            statutSave="Insuffisant";
        }else{
            statutSave="Faible";
        }

        return StockArticleResponseDto.builder()
                .id(stockArticle.getId())
                .qte(stockArticle.getQte())
                .statut(statutSave)
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
