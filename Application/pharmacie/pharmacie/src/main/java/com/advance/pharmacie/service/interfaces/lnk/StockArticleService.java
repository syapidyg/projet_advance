package com.advance.pharmacie.service.interfaces.lnk;

import com.advance.pharmacie.dto.dtoRequest.StockArticleRequestDto;
import com.advance.pharmacie.dto.dtoResponse.StockArticleResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StockArticleService {

//    StockArticleResponseDto createOrUpdate(StockArticleRequestDto dtoCaisse);

    void createOrUpdate(List<StockArticleRequestDto> dtoStockArticles);


    StockArticleResponseDto readOne(Long id);

    Page<StockArticleResponseDto> read(String token, Pageable pageable);

    String delete(Long id);

    Long checkEtatStockArticle(Long idProduit, Long idDepot);

    Boolean destockArticle(Long idProduit, Long idDepot, Long qte);

    StockArticleResponseDto addStockArticle(Long idProduit, Long idDepot, Long qte);

    StockArticleResponseDto addStockArticle(StockArticleRequestDto dtoStockArticle);
}
