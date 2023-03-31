package com.advance.pharmacie.service.implementations;

import com.advance.pharmacie.dto.dtoRequest.LigneCommandeRequestDto;
import com.advance.pharmacie.dto.dtoResponse.LigneCommandeResponseDto;
import com.advance.pharmacie.model.Commande;
import com.advance.pharmacie.model.Produit;
import com.advance.pharmacie.model.lnk.LigneCommande;
import com.advance.pharmacie.repository.CommandeRepository;
import com.advance.pharmacie.repository.ProduitRepository;
import com.advance.pharmacie.repository.lnk.LigneCommandeRepository;
import com.advance.pharmacie.service.interfaces.lnk.LigneCommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class LigneCommandeImplementation implements LigneCommandeService {

    @Autowired
    LigneCommandeRepository ligneCommandeRepository;

    @Autowired
    CommandeRepository commandeRepository;

    @Autowired
    ProduitRepository produitRepository;

    @Override
    public LigneCommandeResponseDto createOrUpdate(LigneCommandeRequestDto dtoLigneCommande) {

        Commande commande = commandeRepository.findById(dtoLigneCommande.getIdCommande())
                .orElseThrow(() -> new RuntimeException("Commande non existant"));

        Produit produit = produitRepository.findById(dtoLigneCommande.getIdProduit())
                .orElseThrow(() -> new RuntimeException("Produit non existant"));

        if (Objects.nonNull(dtoLigneCommande.getId()) && dtoLigneCommande.getId() > 0) {

            LigneCommande ligneCommande = ligneCommandeRepository.findById(dtoLigneCommande.getId()).map(p -> {
                p.setPt(dtoLigneCommande.getPt());
                p.setQte(dtoLigneCommande.getQte());
                p.setCommande(commande);
                p.setProduit(produit);
                return ligneCommandeRepository.save(p);
            }).orElseThrow(() -> new RuntimeException(("LigneCommande introuvable")));

            return LigneCommandeResponseDto.entityToDto(ligneCommandeRepository.save(ligneCommande));
        }

        LigneCommande ligneCommande = LigneCommandeRequestDto.dtoToEntity(dtoLigneCommande, commande, produit);
        return LigneCommandeResponseDto.entityToDto(ligneCommandeRepository.save(ligneCommande));

    }

    @Override
    public List<LigneCommandeResponseDto> read() {

        List<LigneCommande> ligneCommande = ligneCommandeRepository.findAll();
        return LigneCommandeResponseDto.entityToDtoList(ligneCommande);
    }

    @Override
    public String delete(Long id) {

        ligneCommandeRepository.deleteById(id);
        return "LigneCommande suprimé avec succès";
    }

    @Override
    public LigneCommandeResponseDto readOne(Long id) {
        LigneCommande ligneCommande = ligneCommandeRepository.findById(id).orElseThrow(() -> new RuntimeException("Aucun LigneCommande ne correspond a cet ID"));
        ;
        return LigneCommandeResponseDto.entityToDto(ligneCommande);
    }

    @Override
    public List<LigneCommandeResponseDto> readClient(Long id) {
        List<LigneCommande> ligneCommande = ligneCommandeRepository.findByCommandeId(id);
        return LigneCommandeResponseDto.entityToDtoList(ligneCommande);
    }

    @Override
    public List<LigneCommandeResponseDto> readFournisseur(Long id) {
        List<LigneCommande> ligneCommande = ligneCommandeRepository.findByCommandeId(id);
        return LigneCommandeResponseDto.entityToDtoList(ligneCommande);
    }

    @Override
    public List<LigneCommandeResponseDto> readStock(Long id) {
        List<LigneCommande> ligneCommande = ligneCommandeRepository.findByCommandeId(id);
        return LigneCommandeResponseDto.entityToDtoList(ligneCommande);
    }
}
