package com.advance.pharmacie.service.implementations;

import com.advance.pharmacie.dto.dtoRequest.ProduitRequestDto;
import com.advance.pharmacie.dto.dtoResponse.CommandeResponseDto;
import com.advance.pharmacie.dto.dtoResponse.ProduitResponseDto;
import com.advance.pharmacie.exception.BadRequestException;
import com.advance.pharmacie.model.Client;
import com.advance.pharmacie.model.Famille;
import com.advance.pharmacie.model.Numerotation;
import com.advance.pharmacie.model.Produit;
import com.advance.pharmacie.repository.FamilleRepository;
import com.advance.pharmacie.repository.NumerotationRepository;
import com.advance.pharmacie.repository.ProduitRepository;
import com.advance.pharmacie.service.interfaces.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Objects;

@Service
public class ProduitImplementation implements ProduitService {

    @Autowired
    ProduitRepository produitRepository;

    @Autowired
    FamilleRepository familleRepository;

    @Autowired
    NumerotationRepository numerotationRepository;

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

        dtoProduit.setCode(getCodeCourant());
//        dtoProduit.setCode(null);
        Produit produit = ProduitRequestDto.dtoToEntity(dtoProduit, famille);
        return ProduitResponseDto.entityToDto(produitRepository.save(produit));

    }

    @Override
    public Page<ProduitResponseDto> read(String token, Pageable pageable) {

        return ProduitResponseDto.entityToDtoList(produitRepository.findAllProduit("%"+token+"%", pageable));

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

    public String getCodeCourant() {
        Numerotation numerotation = numerotationRepository.findByCode("PRODUIT").orElse(null);
        if (Objects.isNull(numerotation)) {
            // Si la souche de numérotation n'existe pas, créer une nouvelle souche avec un numéro d'index initial de 1
            numerotation = new Numerotation();
            numerotation.setCode("PRODUIT");
            numerotation.setSouche("PRE");
            numerotation.setNumeroIndex(1L);
            numerotationRepository.save(numerotation);
        }

        // Récupérer la souche et le numéro d'index courants
        String souche = numerotation.getSouche();
        Long numeroIndex = numerotation.getNumeroIndex();

        // Générer le code avec la souche et le numéro d'index courants
        String code = souche.concat(String.format("%05d", numeroIndex));

        // Vérifier si le code existe déjà dans la base de données
        while (produitRepository.existsByCode(code)) {
            // Si le code existe, incrémenter le numéro d'index et générer un nouveau code
            numeroIndex++;
            numerotation.setNumeroIndex(numeroIndex);
            numerotation = numerotationRepository.save(numerotation);
            code = souche.concat(String.format("%05d", numeroIndex));
        }

        // Retourner le code généré
        return code;
    }
}
