package com.advance.pharmacie.dto.dtoRequest;

import com.advance.pharmacie.model.Depot;
import com.advance.pharmacie.model.Produit;
import com.advance.pharmacie.model.StockArticle;
import lombok.Builder;
import lombok.Data;

import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
