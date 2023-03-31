package com.advance.pharmacie.service.implementations;

import com.advance.pharmacie.dto.dtoRequest.StockArticleRequestDto;
import com.advance.pharmacie.dto.dtoResponse.CommandeResponseDto;
import com.advance.pharmacie.dto.dtoResponse.StockArticleResponseDto;
import com.advance.pharmacie.exception.BadRequestException;
import com.advance.pharmacie.exception.ResourceNotFoundException;
import com.advance.pharmacie.model.Depot;
import com.advance.pharmacie.model.Produit;
import com.advance.pharmacie.model.lnk.StockArticle;
import com.advance.pharmacie.repository.DepotRepository;
import com.advance.pharmacie.repository.ProduitRepository;
import com.advance.pharmacie.repository.VariableGlobaleRepository;
import com.advance.pharmacie.repository.lnk.StockArticleRepository;
import com.advance.pharmacie.service.interfaces.lnk.StockArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    @Autowired
    VariableGlobaleRepository variableGlobaleRepository;

    @Override
    public void createOrUpdate(List<StockArticleRequestDto> dtoStockArticles) {

        dtoStockArticles.forEach(stockArticleRequestDto -> {

            if (Objects.nonNull(stockArticleRequestDto)) {

                addStockArticle(stockArticleRequestDto);

            } else {
                throw new BadRequestException("Inserez des lignes de stockArticle");
            }

        });


    }

    @Override
    public Page<StockArticleResponseDto> read(String token, Pageable pageable) {

        Long qteMAX = Long.parseLong(variableGlobaleRepository.findByCle("MAX").getValeur());
        Long qteMIN = Long.parseLong(variableGlobaleRepository.findByCle("MIN").getValeur());
        Long qteALERTE = Long.parseLong(variableGlobaleRepository.findByCle("ALERTE").getValeur());
        return StockArticleResponseDto.entityToDtoListStatut(stockArticleRepository.findAllStockArticle("%"+token+"%", pageable), qteMAX, qteALERTE, qteMIN);

    }

    @Override
    public String delete(Long id) {

        stockArticleRepository.deleteById(id);
        return "StockArticle suprimé avec succès";
    }

    @Override
    public StockArticleResponseDto readOne(Long id) {
        StockArticle stockArticle = stockArticleRepository.findById(id).orElseThrow(() -> new RuntimeException("Aucun StockArticle ne correspond a cet ID"));

        return StockArticleResponseDto.entityToDto(stockArticle);
    }

    @Override
    public Long checkEtatStockArticle(Long idProduit, Long idDepot) {

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
    public StockArticleResponseDto addStockArticle(Long idProduit, Long idDepot, Long qte) {

        Produit produit = produitRepository.findById(idProduit)
                .orElseThrow(() -> new ResourceNotFoundException("Produit", "id", idProduit));

        Depot depot = depotRepository.findById(idDepot)
                .orElseThrow(() -> new ResourceNotFoundException("Depot", "id", idDepot));

        Optional<StockArticle> stockArticle = stockArticleRepository.findByDepotAndProduit(depot, produit);

        if (stockArticle.isPresent()) {

            stockArticle.get().setQte(stockArticle.get().getQte() + qte);
            return StockArticleResponseDto.entityToDto(stockArticleRepository.save(stockArticle.get()));
        } else {
            Long qteMAX = Long.parseLong(variableGlobaleRepository.findByCle("MAX").getValeur());
            Long qteMIN = Long.parseLong(variableGlobaleRepository.findByCle("MIN").getValeur());
            Long qteALERTE = Long.parseLong(variableGlobaleRepository.findByCle("ALERTE").getValeur());
            StockArticle stockArticle1 = StockArticleRequestDto.dtoToEntityFromFournisseur(depot, produit, qte, qteMAX, qteMIN, qteALERTE);
            return StockArticleResponseDto.entityToDto(stockArticleRepository.save(stockArticle1));

        }

    }

    @Override
    public StockArticleResponseDto addStockArticle(StockArticleRequestDto dtoStockArticle) {

        Produit produit = produitRepository.findById(dtoStockArticle.getIdProduit())
                .orElseThrow(() -> new ResourceNotFoundException("Produit", "id", dtoStockArticle.getIdProduit()));

        Depot depot = depotRepository.findById(dtoStockArticle.getIdDepot())
                .orElseThrow(() -> new ResourceNotFoundException("Depot", "id", dtoStockArticle.getIdDepot()));

        Optional<StockArticle> stockArticle = stockArticleRepository.findByDepotAndProduit(depot, produit);

        if (stockArticle.isPresent()) {

            stockArticle.get().setQte(stockArticle.get().getQte() + dtoStockArticle.getQte());

            return StockArticleResponseDto.entityToDto(stockArticleRepository.save(stockArticle.get()));
        } else {
            Long qteMAX = Long.parseLong(variableGlobaleRepository.findByCle("MAX").getValeur());
            Long qteMIN = Long.parseLong(variableGlobaleRepository.findByCle("MIN").getValeur());
            Long qteALERTE = Long.parseLong(variableGlobaleRepository.findByCle("ALERTE").getValeur());
            StockArticle stockArticle1 = StockArticleRequestDto.dtoToEntity(dtoStockArticle, depot, produit);
            return StockArticleResponseDto.entityToDtoStatut(stockArticleRepository.save(stockArticle1), qteMAX, qteMIN, qteALERTE );
        }


    }

}
