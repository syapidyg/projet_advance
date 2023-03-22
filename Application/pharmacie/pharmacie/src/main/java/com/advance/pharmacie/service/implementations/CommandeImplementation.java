package com.advance.pharmacie.service.implementations;

import com.advance.pharmacie.dto.dtoRequest.BonToFactureRequestDto;
import com.advance.pharmacie.dto.dtoRequest.CommandeRequestDto;
import com.advance.pharmacie.dto.dtoRequest.LigneCommandeRequestDto;
import com.advance.pharmacie.dto.dtoResponse.CommandeResponseDto;
import com.advance.pharmacie.dto.dtoResponse.LigneCommandeResponseDto;
import com.advance.pharmacie.exception.BadRequestException;
import com.advance.pharmacie.exception.ResourceNotFoundException;
import com.advance.pharmacie.model.*;
import com.advance.pharmacie.model.lnk.LigneCommande;
import com.advance.pharmacie.repository.*;
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

    @Autowired
    DepotRepository depotRepository;

   @Autowired
    NumerotationRepository numerotationRepository;

    @Transactional
    @Override
    public CommandeResponseDto createOrUpdate(CommandeRequestDto dtoCommande) {

        Client client = null;
        Fournisseur fournisseur = null;

        Depot depot = depotRepository.findById(dtoCommande.getIdDepot())
                .orElseThrow(() -> new ResourceNotFoundException("depot", "id", dtoCommande.getIdDepot()));


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
                p.setDocument(dtoCommande.getDocument());
                p.setPt(dtoCommande.getPt());
                p.setType(dtoCommande.getType());
                p.setDepot(depot);
                if (finalClient != null) {
                    p.setClient(finalClient);
                } else if (finalFournisseur != null) {
                    p.setFournisseur(finalFournisseur);
                }
                return commandeRepository.save(p);
            }).orElseThrow(() -> new RuntimeException(("Commande introuvable")));

            return CommandeResponseDto.entityToDto(commandeRepository.save(commande));
        } else {

            Commande commandeToSave = CommandeRequestDto.dtoToEntity(dtoCommande, client, fournisseur, depot);
            commandeToSave.setCode(getCodeCourant());
            Commande commande = commandeRepository.save(commandeToSave);

            if (dtoCommande.getType().equals("client")) {


                if (dtoCommande.getLigneCommandes() != null) {

                    dtoCommande.getLigneCommandes().forEach(ligneCommandedto -> {

                        Produit produit = produitRepository.findById(ligneCommandedto.getIdProduit())
                                .orElseThrow(
                                        () -> new RuntimeException("Un Id de produit inseré est invalide"));
                        LigneCommande ligneCommandeToSave = LigneCommandeRequestDto
                                .dtoToEntity(ligneCommandedto, commande, produit);

                        if (dtoCommande.getDocument().equals("Bon de commande")) {

                            ligneCommandeRepository.save(ligneCommandeToSave);

                        } else if (dtoCommande.getDocument().equals("Facture")) {
                            //cas de la facture
                            Long qteTest = stockArticleService.checkEtatStockArticle(ligneCommandeToSave.getProduit().getId(), dtoCommande.getIdDepot());
                            //campare la Qte checker a la quantité
                            if (qteTest < ligneCommandeToSave.getQte())
                                throw new BadRequestException("Stock insuffisant pour ce produit");

                            stockArticleService.destockArticle(ligneCommandeToSave.getProduit().getId(), dtoCommande.getIdDepot(), ligneCommandeToSave.getQte());

                            ligneCommandeRepository.save(ligneCommandeToSave);

                        } else {
                            throw new BadRequestException("Choississez le type de document");
                        }
                    });
                }

                return CommandeResponseDto.entityToDto(commande, ligneCommandeRepository.findByCommandeId(commande.getId()));


            } else if (dtoCommande.getType().equals("fournisseur")) {
                fournisseur = fournisseurRepository.findById(dtoCommande.getIdClientFournisseur())
                        .orElseThrow(() -> new RuntimeException("Fournisseur non existant"));

                if (dtoCommande.getLigneCommandes() != null) {

                    dtoCommande.getLigneCommandes().forEach(ligneCommandedto -> {

                        Produit produit = produitRepository.findById(ligneCommandedto.getIdProduit())
                                .orElseThrow(
                                        () -> new RuntimeException("Un Id de produit inseré est invalide"));
                        LigneCommande ligneCommandeToSave = LigneCommandeRequestDto
                                .dtoToEntity(ligneCommandedto, commande, produit);

                        if (dtoCommande.getDocument().equals("Bon de commande")) {

                            ligneCommandeRepository.save(ligneCommandeToSave);

                        } else {
                            //cas de la facture
                            stockArticleService.addStockArticle(ligneCommandeToSave.getProduit().getId(), dtoCommande.getIdDepot(), ligneCommandeToSave.getQte());

                            ligneCommandeRepository.save(ligneCommandeToSave);

                        }
                    });
                }
                return CommandeResponseDto.entityToDto(commande, ligneCommandeRepository.findByCommandeId(commande.getId()));

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
    public List<CommandeResponseDto> readClient() {

        List<Commande> commandes = commandeRepository.findByType("client");
        return CommandeResponseDto.entityToDtoList(commandes);
    }

    @Override
    public List<CommandeResponseDto> readFournisseur() {

        List<Commande> commandes = commandeRepository.findByType("fournisseur");
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

    @Override
    public CommandeResponseDto tBonFacture(BonToFactureRequestDto dto) {
        Commande commandeToEdit = commandeRepository.findById(dto.getIdCommande())
                .orElseThrow(() -> new ResourceNotFoundException("Commande", "id", dto.getIdCommande()));

        List<LigneCommande> ligneCommandes = commandeToEdit.getLigneCommande();

        Long idCF = commandeToEdit.getType().equals("client") ? commandeToEdit.getClient().getId() : commandeToEdit.getFournisseur().getId();

        CommandeRequestDto commandeToSave = CommandeRequestDto.builder()
                .document("Facture")
                .idClientFournisseur(idCF)
                .idDepot(dto.getIdDepot())
                .type(commandeToEdit.getType())
                .pt(commandeToEdit.getPt())
                .LigneCommandes(LigneCommandeRequestDto.entityToDtoListT(commandeToEdit.getLigneCommande()))
                .build();

        return createOrUpdate(commandeToSave);

    }

    public String getCodeCourant() {
        Numerotation numerotation = numerotationRepository.findByCode("COMMANDE").orElse(null);
        if (Objects.isNull(numerotation)) {
            // Si la souche de numérotation n'existe pas, créer une nouvelle souche avec un numéro d'index initial de 1
            numerotation = new Numerotation();
            numerotation.setCode("COMMANDE");
            numerotation.setSouche("CMD");
            numerotation.setNumeroIndex(1L);
            numerotationRepository.save(numerotation);
        }

        // Récupérer la souche et le numéro d'index courants
        String souche = numerotation.getSouche();
        Long numeroIndex = numerotation.getNumeroIndex();

        // Générer le code avec la souche et le numéro d'index courants
        String code = souche.concat(String.format("%03d", numeroIndex));

        // Vérifier si le code existe déjà dans la base de données
        while (commandeRepository.existsByCode(code)) {
            // Si le code existe, incrémenter le numéro d'index et générer un nouveau code
            numeroIndex++;
            numerotation.setNumeroIndex(numeroIndex);
            numerotation = numerotationRepository.save(numerotation);
            code = souche.concat(String.format("%03d", numeroIndex));
        }

        // Retourner le code généré
        return code;
    }

}
