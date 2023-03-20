package com.advance.pharmacie.service.implementations;

import com.advance.pharmacie.dto.dtoRequest.StockArticleRequestDto;
import com.advance.pharmacie.dto.dtoResponse.StockArticleResponseDto;
import com.advance.pharmacie.exception.BadRequestException;
import com.advance.pharmacie.exception.ErrorMessage;
import com.advance.pharmacie.exception.ResourceNotFoundException;
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
import java.util.Optional;

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

    @Override
    public Long checkEtatStockArticle(Long idProduit, Long idDepot){

        Produit produit = produitRepository.findById(idProduit)
                .orElseThrow(() -> new ResourceNotFoundException("Produit", "id", idProduit));

        Depot depot = depotRepository.findById(idDepot)
                .orElseThrow(() -> new ResourceNotFoundException("Depot", "id", idDepot));

        StockArticle stockArticle = stockArticleRepository.findByDepotAndProduit(depot, produit)
                .orElseThrow(() -> new BadRequestException("Aucune produit en stock ne correspond"));

        return stockArticle.getQte();
    }

    @Override
    public Boolean destockArticle(Long idProduit, Long idDepot, Long qte) {

        Produit produit = produitRepository.findById(idProduit)
                .orElseThrow(() -> new ResourceNotFoundException("Produit", "id", idProduit));

        Depot depot = depotRepository.findById(idDepot)
                .orElseThrow(() -> new ResourceNotFoundException("Depot", "id", idDepot));

        StockArticle stockArticle = stockArticleRepository.findByDepotAndProduit(depot, produit)
                .orElseThrow(() -> new BadRequestException("Aucune produit en stock ne correspond"));

        stockArticle.setQte(stockArticle.getQte() - qte);

        stockArticleRepository.save(stockArticle);

        return true;

    }

    @Override
    public Boolean addStockArticle(Long idProduit, Long idDepot, Long qte) {

        Produit produit = produitRepository.findById(idProduit)
                .orElseThrow(() -> new ResourceNotFoundException("Produit", "id", idProduit));

        Depot depot = depotRepository.findById(idDepot)
                .orElseThrow(() -> new ResourceNotFoundException("Depot", "id", idDepot));

       Optional <StockArticle> stockArticle = stockArticleRepository.findByDepotAndProduit(depot, produit);

        if (stockArticle.isPresent()){

            stockArticle.get().setQte(stockArticle.get().getQte()+ qte);
            stockArticleRepository.save(stockArticle.get());
            return true;
        }else {
            StockArticle stockArticle1 = StockArticleRequestDto.dtoToEntityFromFournisseur(depot, produit, qte);
            StockArticleResponseDto.entityToDto(stockArticleRepository.save(stockArticle1));
            return true;
        }

//        StockArticle stockArticle = stockArticleRepository.findByDepotAndProduit(depot, produit)
//                .orElseThrow(() -> new BadRequestException("Aucune produit en stock ne correspond"));
//
//        stockArticle.setQte(stockArticle.getQte() + qte);
//


    }


}
