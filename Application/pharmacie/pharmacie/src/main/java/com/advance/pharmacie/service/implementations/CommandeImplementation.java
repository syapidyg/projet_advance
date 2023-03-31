package com.advance.pharmacie.service.implementations;

import com.advance.pharmacie.dto.dtoRequest.BonToFactureRequestDto;
import com.advance.pharmacie.dto.dtoRequest.CommandeRequestDto;
import com.advance.pharmacie.dto.dtoRequest.LigneCommandeRequestDto;
import com.advance.pharmacie.dto.dtoResponse.CommandeResponseDto;
import com.advance.pharmacie.exception.BadRequestException;
import com.advance.pharmacie.exception.ResourceNotFoundException;
import com.advance.pharmacie.model.*;
import com.advance.pharmacie.model.lnk.LigneCommande;
import com.advance.pharmacie.model.lnk.StockArticle;
import com.advance.pharmacie.repository.*;
import com.advance.pharmacie.repository.lnk.LigneCommandeRepository;
import com.advance.pharmacie.repository.lnk.StockArticleRepository;
import com.advance.pharmacie.service.interfaces.CommandeService;
import com.advance.pharmacie.service.interfaces.lnk.StockArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.advance.pharmacie.dto.dtoRequest.LigneCommandeRequestDto.dtoToEntity;

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

    @Autowired
    StockArticleRepository stockArticleRepository;


    @Transactional
    @Override
    public CommandeResponseDto createOrUpdate(CommandeRequestDto dtoCommande) {

        Client client = null;
        Fournisseur fournisseur = null;
        Depot depotDestination = null;
        Depot depot = null;
        depot = depotRepository.findById(dtoCommande.getIdDepot())
                .orElseThrow(() -> new ResourceNotFoundException("depot", "id", dtoCommande.getIdDepot()));

        if (dtoCommande.getType().equals("client")) {
            client = clientRepository.findById(dtoCommande.getIdClientFournisseur())
                    .orElseThrow(() -> new ResourceNotFoundException("client", "id", dtoCommande.getIdClientFournisseur()));
        } else if (dtoCommande.getType().equals("fournisseur")) {
            fournisseur = fournisseurRepository.findById(dtoCommande.getIdClientFournisseur())
                    .orElseThrow(() -> new RuntimeException("Fournisseur non existant"));
        } else if (dtoCommande.getType().equals("stock") && dtoCommande.getDocument().equals("Entree en stock")) {

            depot = depotRepository.findById(dtoCommande.getIdDepot())
                    .orElseThrow(() -> new ResourceNotFoundException("depot", "id", dtoCommande.getIdDepot()));
        } else {
            depotDestination = depotRepository.findById(dtoCommande.getIdDepotDestination())
                    .orElseThrow(() -> new ResourceNotFoundException("depot destination", "idDepotDestination", dtoCommande.getIdDepotDestination()));

        }

        if (Objects.nonNull(dtoCommande.getId()) && dtoCommande.getId() > 0) {
            Fournisseur finalFournisseur = fournisseur;
            Client finalClient = client;
            Depot finalDepot = depot;
            Commande commande = commandeRepository.findById(dtoCommande.getId()).map(p -> {
                p.setDocument(dtoCommande.getDocument());
                p.setPt(dtoCommande.getPt());
                p.setType(dtoCommande.getType());
                p.setDepot(finalDepot);
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

            switch (dtoCommande.getType()) {
                case "client":
                    if (dtoCommande.getLigneCommandes() != null) {
                        dtoCommande.getLigneCommandes().forEach(ligneCommandedto -> {
                            Produit produit = produitRepository.findById(ligneCommandedto.getIdProduit())
                                    .orElseThrow(
                                            () -> new RuntimeException("Un Id de produit inseré est invalide"));
                            LigneCommande ligneCommandeToSave = dtoToEntity(ligneCommandedto, commande, produit);

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

                case "fournisseur":
                    fournisseur = fournisseurRepository.findById(dtoCommande.getIdClientFournisseur())
                            .orElseThrow(() -> new RuntimeException("Fournisseur non existant"));

                    if (dtoCommande.getLigneCommandes() != null) {
                        dtoCommande.getLigneCommandes().forEach(ligneCommandedto -> {
                            Produit produit = produitRepository.findById(ligneCommandedto.getIdProduit())
                                    .orElseThrow(
                                            () -> new RuntimeException("Un Id de produit inseré est invalide"));
                            LigneCommande ligneCommandeToSave = dtoToEntity(ligneCommandedto, commande, produit);

                            if (!dtoCommande.getDocument().equals("Bon de commande")) {
                                //cas de la facture
                                stockArticleService.addStockArticle(ligneCommandeToSave.getProduit().getId(), dtoCommande.getIdDepot(), ligneCommandeToSave.getQte());
                            }
                            ligneCommandeRepository.save(ligneCommandeToSave);
                        });
                    }
                    return CommandeResponseDto.entityToDto(commande, ligneCommandeRepository.findByCommandeId(commande.getId()));

                case "stock":
                    if (dtoCommande.getDocument().equals("Entree en stock")) {
                        if (dtoCommande.getLigneCommandes() != null) {
                            Depot finalDepot1 = depot;
                            dtoCommande.getLigneCommandes().forEach(ligneCommandedto -> {
                                Produit produit = produitRepository.findById(ligneCommandedto.getIdProduit())
                                        .orElseThrow(
                                                () -> new RuntimeException("Un Id de produit inseré est invalide"));
                                Optional<StockArticle> stockArticle = stockArticleRepository.findByDepotAndProduit(finalDepot1, produit);

                                if (stockArticle.isPresent()) {
                                    stockArticle.get().setQte(stockArticle.get().getQte() + ligneCommandedto.getQte());
                                    ligneCommandedto.setPt((long) 0);
                                    LigneCommande ligneCommandeToSave = dtoToEntity(ligneCommandedto, commande, produit);
                                    ligneCommandeRepository.save(ligneCommandeToSave);

                                } else {
                                    ligneCommandedto.setPt((long) 0);
                                    LigneCommande ligneCommandeToSave = dtoToEntity(ligneCommandedto, commande, produit);
                                    ligneCommandeRepository.save(ligneCommandeToSave);
                                    stockArticleService.addStockArticle(ligneCommandeToSave.getProduit().getId(), dtoCommande.getIdDepot(), ligneCommandeToSave.getQte());
                                    ligneCommandeRepository.save(ligneCommandeToSave);
                                }

                            });
                            return CommandeResponseDto.entityToDto(commande, ligneCommandeRepository.findByCommandeId(commande.getId()));
                        }
                    } else {
                        if (dtoCommande.getLigneCommandes() != null) {
                            Depot finalDepotDestination = depotDestination;
                            Depot finalDepot2 = depot;
                            dtoCommande.getLigneCommandes().forEach(ligneCommandedto -> {
                                Produit produit = produitRepository.findById(ligneCommandedto.getIdProduit())
                                        .orElseThrow(
                                                () -> new RuntimeException("Un Id de produit inseré est invalide"));

                                StockArticle stockArticle = stockArticleRepository.findByDepotAndProduit(finalDepot2, produit)
                                        .orElseThrow(
                                                () -> new RuntimeException("Cet article n'existe pas dans ce depot"));

                                ligneCommandedto.setPt((long) 0);
                                LigneCommande ligneCommandeToSave = dtoToEntity(ligneCommandedto, commande, produit);
                                ligneCommandeRepository.save(ligneCommandeToSave);
                                stockArticleService.destockArticle(ligneCommandeToSave.getProduit().getId(), dtoCommande.getIdDepot(), ligneCommandeToSave.getQte());
                                stockArticleService.addStockArticle(produit.getId(), finalDepotDestination.getId(), ligneCommandedto.getQte());
                            });
                            return CommandeResponseDto.entityToDtoDepot(commande, ligneCommandeRepository.findByCommandeId(commande.getId()), depotDestination.getId());

                        } else {
                            throw new BadRequestException("Aucune ligne inséréer!");
                        }
                    }

                default:
                    throw new RuntimeException("Choississez un type a inserer et une personne valide");

            }
        }
    }

    @Override
    public Page<CommandeResponseDto> read(String type, String token, Pageable pageable) {
        return CommandeResponseDto.entityToDtoList(commandeRepository.findAllCommande("%"+type+"%","%"+token+"%", pageable));
    }

    @Override
    public Page<CommandeResponseDto> readClient(String type, String document, String token, Pageable pageable) {
        Page<Commande> commandes = commandeRepository.findAllCommandeTypeClient(type, "%"+document+"%","%"+token+"%", pageable);
        return CommandeResponseDto.entityToDtoList(commandes);
    }

    @Override
    public Page<CommandeResponseDto> readFournisseur(String type, String token, Pageable pageable) {
        Page<Commande> commandes = commandeRepository.findAllCommandeTypeFournisseur(type,"%"+token+"%", pageable);
        return CommandeResponseDto.entityToDtoList(commandes);
    }

  @Override
    public Page<CommandeResponseDto> readStock(String type, String token, Pageable pageable) {
        Page<Commande> commandes = commandeRepository.findAllCommandeTypeStock(type,"%"+token+"%", pageable);
        return CommandeResponseDto.entityToDtoList(commandes);
    }

    @Override
    public Page<CommandeResponseDto> readByType(String type, String document, String dateDebut, String dateFin, String token, Pageable pageable, int sizeOf) {


        Page<Commande> commandes = commandeRepository.findAllCommandeTypeOrTypeAndDocument(type,"%"+document+"%", "%"+token+"%", dateDebut, dateFin, pageable, sizeOf);
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
