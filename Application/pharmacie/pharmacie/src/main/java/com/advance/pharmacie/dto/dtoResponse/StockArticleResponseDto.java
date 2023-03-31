package com.advance.pharmacie.dto.dtoResponse;

import com.advance.pharmacie.model.Commande;
import com.advance.pharmacie.model.Produit;
import com.advance.pharmacie.model.lnk.StockArticle;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Objects;
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

    public static StockArticleResponseDto entityToDtoStatut(StockArticle stockArticle, Long qteMaximale, Long qteAlerte, Long qteMinimale) {

        String statutSave;
        Long tempStockMax = (Objects.nonNull(stockArticle.getQteMaximale()) && stockArticle.getQteMaximale()>0 ) ? stockArticle.getQteMaximale(): qteMaximale ;
        Long tempStockMin = (Objects.nonNull(stockArticle.getQteMinimale()) && stockArticle.getQteMinimale()>0 ) ? stockArticle.getQteMinimale(): qteMinimale ;
        Long tempStockAlerte = (Objects.nonNull(stockArticle.getQteAlerte()) && stockArticle.getQteAlerte()>0 ) ? stockArticle.getQteAlerte(): qteAlerte ;

        if (stockArticle.getQte() > tempStockMax) {
            statutSave = "En excès";
        } else if (stockArticle.getQte() >= tempStockMin && stockArticle.getQte() <= tempStockMax) {
            statutSave = "Normal";
        } else if (stockArticle.getQte() < tempStockMin && stockArticle.getQte() > tempStockAlerte) {
            statutSave = "Insuffisant";
        } else {
            statutSave = "Faible";
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

    public static List<StockArticleResponseDto> entityToDtoListStatut(List<StockArticle> stockArticleList, Long qteMaximale, Long qteAlerte, Long qteMinimale) {
        return stockArticleList.stream().map( stockArticle ->  StockArticleResponseDto.entityToDtoStatut(stockArticle, qteMaximale, qteAlerte, qteMinimale)).collect(Collectors.toList());
    }

    public static Page<StockArticleResponseDto> entityToDtoListStatut(Page<StockArticle> stockArticleList, Long qteMaximale, Long qteAlerte, Long qteMinimale){
        return stockArticleList.map( stockArticle ->  StockArticleResponseDto.entityToDtoStatut(stockArticle, qteMaximale, qteAlerte, qteMinimale));
    }
}


