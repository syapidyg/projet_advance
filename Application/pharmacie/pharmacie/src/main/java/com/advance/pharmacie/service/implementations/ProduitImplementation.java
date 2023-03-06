package com.advance.pharmacie.service.implementations;

import com.advance.pharmacie.dto.dtoRequest.ProduitRequestDto;
import com.advance.pharmacie.dto.dtoResponse.ProduitResponseDto;
import com.advance.pharmacie.model.Client;
import com.advance.pharmacie.model.Famille;
import com.advance.pharmacie.model.Produit;
import com.advance.pharmacie.repository.FamilleRepository;
import com.advance.pharmacie.repository.ProduitRepository;
import com.advance.pharmacie.service.interfaces.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProduitImplementation implements ProduitService {

    @Autowired
    ProduitRepository produitRepository;

    @Autowired
    FamilleRepository familleRepository;

    @Override
    public ProduitResponseDto createOrUpdate(ProduitRequestDto dtoProduit) {

        Famille famille = familleRepository.findById(dtoProduit.getIdfamille())
                .orElseThrow(() -> new RuntimeException("Inserez la famille"));

        if (Objects.nonNull(dtoProduit.getId()) &&  dtoProduit.getId() > 0) {

            Produit produit = produitRepository.findById(dtoProduit.getId()).map(p -> {
                p.setDci(dtoProduit.getDci());
                p.setDosage(dtoProduit.getDosage());
                p.setForme(dtoProduit.getForme());
                p.setCategorie(dtoProduit.getCategorie());
                p.setPa(dtoProduit.getPa());
                p.setPv(dtoProduit.getPv());
                p.setFamille(famille);
                return produitRepository.save(p);
            }).orElseThrow(() -> new RuntimeException(("Produit introuvable")));

            return ProduitResponseDto.entityToDto(produitRepository.save(produit));
        }

        Produit produit = ProduitRequestDto.dtoToEntity(dtoProduit, famille);
        return ProduitResponseDto.entityToDto(produitRepository.save(produit));

    }

    @Override
    public List<ProduitResponseDto> read() {

        List<Produit> produits = produitRepository.findAll();
        return ProduitResponseDto.entityToDtoList(produits);
    }

    @Override
    public String delete(Long id) {

        produitRepository.deleteById(id);
        return "Produit suprimé avec succès";
    }

    @Override
    public ProduitResponseDto readOne(Long id) {
        Produit produit = produitRepository.findById(id).orElseThrow(() -> new RuntimeException("Aucun Produit ne correspond a cet ID"));;
        return ProduitResponseDto.entityToDto(produit);
    }
}
