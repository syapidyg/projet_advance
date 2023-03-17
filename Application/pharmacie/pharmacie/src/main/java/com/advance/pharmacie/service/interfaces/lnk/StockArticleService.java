package com.advance.pharmacie.service.interfaces.lnk;

import com.advance.pharmacie.dto.dtoRequest.StockArticleRequestDto;
import com.advance.pharmacie.dto.dtoResponse.StockArticleResponseDto;

import java.util.List;

public interface StockArticleService {

    StockArticleResponseDto createOrUpdate(StockArticleRequestDto dtoCaisse);

    List<StockArticleResponseDto> read();

    StockArticleResponseDto readOne(Long id);

    String delete(Long id);

    Long checkEtatStockArticle(Long idProduit, Long idDepot);

    Boolean destockArticle(Long idProduit, Long idDepot, Long qte);
}
