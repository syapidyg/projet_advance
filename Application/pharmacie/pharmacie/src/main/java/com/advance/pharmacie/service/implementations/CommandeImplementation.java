package com.advance.pharmacie.service.implementations;

import com.advance.pharmacie.dto.dtoRequest.CommandeRequestDto;
import com.advance.pharmacie.dto.dtoRequest.LigneCommandeRequestDto;
import com.advance.pharmacie.dto.dtoResponse.CommandeResponseDto;
import com.advance.pharmacie.dto.dtoResponse.LigneCommandeResponseDto;
import com.advance.pharmacie.exception.BadRequestException;
import com.advance.pharmacie.exception.ResourceNotFoundException;
import com.advance.pharmacie.model.*;
import com.advance.pharmacie.model.lnk.LigneCommande;
import com.advance.pharmacie.repository.ClientRepository;
import com.advance.pharmacie.repository.CommandeRepository;
import com.advance.pharmacie.repository.FournisseurRepository;
import com.advance.pharmacie.repository.ProduitRepository;
import com.advance.pharmacie.repository.lnk.LigneCommandeRepository;
import com.advance.pharmacie.service.interfaces.CommandeService;
import com.advance.pharmacie.service.interfaces.lnk.StockArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CommandeImplementation implements CommandeService {

    @Autowired
    CommandeRepository commandeRepository;

    @Autowired
    FournisseurRepository fournisseurRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ProduitRepository produitRepository;

    @Autowired
    LigneCommandeImplementation ligneCommandeImplementation;

    @Autowired
    StockArticleService stockArticleService;

    @Autowired
    LigneCommandeRepository ligneCommandeRepository;

    @Transactional
    @Override
    public CommandeResponseDto createOrUpdate(CommandeRequestDto dtoCommande) {

        Client client = null;
        Fournisseur fournisseur = null;

        if (dtoCommande.getType().equals("client")) {

            client = clientRepository.findById(dtoCommande.getIdClientFournisseur())
                    .orElseThrow(() -> new ResourceNotFoundException("client", "id", dtoCommande.getIdClientFournisseur()));


        } else if (dtoCommande.getType().equals("fournisseur")) {
            fournisseur = fournisseurRepository.findById(dtoCommande.getIdClientFournisseur())
                    .orElseThrow(() -> new RuntimeException("Fournisseur non existant"));
        } else {
            new RuntimeException("Choississez un type a inserer et une personne valide");
        }


        if (Objects.nonNull(dtoCommande.getId()) && dtoCommande.getId() > 0) {

            Fournisseur finalFournisseur = fournisseur;
            Client finalClient = client;

            Commande commande = commandeRepository.findById(dtoCommande.getId()).map(p -> {
                p.setStatut(dtoCommande.getStatut());
                p.setPt(dtoCommande.getPt());
                p.setType(dtoCommande.getType());
                if (finalClient != null) {
                    p.setClient(finalClient);
                } else if (finalFournisseur != null) {
                    p.setFournisseur(finalFournisseur);
                }
                return commandeRepository.save(p);
            }).orElseThrow(() -> new RuntimeException(("Commande introuvable")));

            return CommandeResponseDto.entityToDto(commandeRepository.save(commande));
        } else {

            Commande commandeToSave = CommandeRequestDto.dtoToEntity(dtoCommande, client, fournisseur);
            Commande commande = commandeRepository.save(commandeToSave);

            if (dtoCommande.getType().equals("client")) {


                if (dtoCommande.getLigneCommandes() != null) {

                    dtoCommande.getLigneCommandes().forEach(ligneCommandedto -> {

                        Produit produit = produitRepository.findById(ligneCommandedto.getIdProduit())
                                .orElseThrow(
                                        () -> new RuntimeException("Un Id de produit inseré est invalide"));
                        LigneCommande ligneCommandeToSave = LigneCommandeRequestDto
                                .dtoToEntity(ligneCommandedto, commande, produit);

                        if (dtoCommande.getStatut().equals("Bon de commande")) {

                            ligneCommandeRepository.save(ligneCommandeToSave);

                        } else {
                            //cas de la facture
                            Long qteTest = stockArticleService.checkEtatStockArticle(ligneCommandeToSave.getProduit().getId(), (long) 1);
                            //campare la Qte checker a la quantité
                            if (qteTest < ligneCommandeToSave.getQte())
                                throw new BadRequestException("Stock insuffisant pour ce produit");

                            stockArticleService.destockArticle(ligneCommandeToSave.getProduit().getId(), (long) 1, ligneCommandeToSave.getQte());

                            ligneCommandeRepository.save(ligneCommandeToSave);

                        }
                    });
                }

                return CommandeResponseDto.entityToDto(commande, ligneCommandeRepository.findByCommandeId(commande.getId()));


            } else if (dtoCommande.getType().equals("fournisseur")) {
                fournisseur = fournisseurRepository.findById(dtoCommande.getIdClientFournisseur())
                        .orElseThrow(() -> new RuntimeException("Fournisseur non existant"));
                return null;
            } else {
                throw new RuntimeException("Choississez un type a inserer et une personne valide");

            }
        }
    }

    @Override
    public List<CommandeResponseDto> read() {

        List<Commande> commandes = commandeRepository.findAll();
        return CommandeResponseDto.entityToDtoList(commandes);
    }

    @Override
    public String delete(Long id) {

        commandeRepository.deleteById(id);
        return "Commande suprimé avec succès";
    }

    @Override
    public CommandeResponseDto readOne(Long id) {
        Commande commande = commandeRepository.findById(id).orElseThrow(() -> new RuntimeException("Aucun Commande ne correspond a cet ID"));
        return CommandeResponseDto.entityToDtoWithLigneCommande(commande);
    }
}
