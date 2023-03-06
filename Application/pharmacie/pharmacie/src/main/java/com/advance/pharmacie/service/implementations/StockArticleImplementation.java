package com.advance.pharmacie.service.implementations;

import com.advance.pharmacie.dto.dtoRequest.StockArticleRequestDto;
import com.advance.pharmacie.dto.dtoResponse.StockArticleResponseDto;
import com.advance.pharmacie.model.Depot;
import com.advance.pharmacie.model.Produit;
import com.advance.pharmacie.model.lnk.StockArticle;
import com.advance.pharmacie.repository.DepotRepository;
import com.advance.pharmacie.repository.ProduitRepository;
import com.advance.pharmacie.repository.lnk.StockArticleRepository;
import com.advance.pharmacie.service.interfaces.lnk.StockArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class StockArticleImplementation implements StockArticleService {

    @Autowired
    StockArticleRepository stockArticleRepository;
    @Autowired
    DepotRepository depotRepository;
    @Autowired
    ProduitRepository produitRepository;

    @Override
    public StockArticleResponseDto createOrUpdate(StockArticleRequestDto dtoStockArticle) {

        Depot depot = depotRepository.findById(dtoStockArticle.getIdDepot())
                .orElseThrow(() -> new RuntimeException("Depot non existant"));

        Produit produit = produitRepository.findById(dtoStockArticle.getIdProduit())
                .orElseThrow(() -> new RuntimeException("Produit non existant"));

        if (Objects.nonNull(dtoStockArticle.getId()) && dtoStockArticle.getId() > 0) {

            StockArticle stockArticle = stockArticleRepository.findById(dtoStockArticle.getId()).map(p -> {
                p.setQte(dtoStockArticle.getQte());
                p.setQteAlerte(dtoStockArticle.getQteAlerte());
                p.setQteMaximale(dtoStockArticle.getQteMaximale());
                p.setQteMinimale(dtoStockArticle.getQteMinimale());
                p.setDepot(depot);
                p.setProduit(produit);
                return stockArticleRepository.save(p);
            }).orElseThrow(() -> new RuntimeException(("StockArticle introuvable")));

            return StockArticleResponseDto.entityToDto(stockArticleRepository.save(stockArticle));
        }

        StockArticle stockArticle = StockArticleRequestDto.dtoToEntity(dtoStockArticle, depot, produit);
        return StockArticleResponseDto.entityToDto(stockArticleRepository.save(stockArticle));

    }

    @Override
    public List<StockArticleResponseDto> read() {

        List<StockArticle> stockArticles = stockArticleRepository.findAll();
        return StockArticleResponseDto.entityToDtoList(stockArticles);
    }

    @Override
    public String delete(Long id) {

        stockArticleRepository.deleteById(id);
        return "StockArticle suprimé avec succès";
    }

    @Override
    public StockArticleResponseDto readOne(Long id) {
        StockArticle stockArticle = stockArticleRepository.findById(id).orElseThrow(() -> new RuntimeException("Aucun StockArticle ne correspond a cet ID"));
        ;
        return StockArticleResponseDto.entityToDto(stockArticle);
    }

}
