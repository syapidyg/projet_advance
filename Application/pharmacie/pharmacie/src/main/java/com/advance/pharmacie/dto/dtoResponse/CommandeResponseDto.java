package com.advance.pharmacie.dto.dtoResponse;

import com.advance.pharmacie.model.Client;
import com.advance.pharmacie.model.Commande;
import com.advance.pharmacie.model.Fournisseur;
import com.advance.pharmacie.model.lnk.LigneCommande;
import com.advance.pharmacie.util.StatutCommande;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class CommandeResponseDto {

    private Long id;
    private String code;
    private Long idDepot;
    private Long idDepotDestination;
    private Long pt;
    private String type;
    private StatutCommande statut;
    private String document;
    private LocalDateTime date_creation;
    private FournisseurResponseDto fournisseur;
    private ClientResponseDto client;
    private List<LigneCommandeResponseDto> ligneCommandes;

    public static CommandeResponseDto entityToDto(Commande commande) {
        return CommandeResponseDto.builder()
                .id(commande.getId())
                .code(commande.getCode())
                .idDepot(commande.getDepot().getId())
                .pt(commande.getPt())
                .statut(commande.getStatut())
                .document(commande.getDocument())
                .type(commande.getType())
                .fournisseur(FournisseurResponseDto.entityToDto(commande.getFournisseur()))
                .client(ClientResponseDto.entityToDto(commande.getClient()))
                .date_creation(commande.getDate_creation())
                //.ligneCommandes(LigneCommandeResponseDto.entityToDtoList(commande.getLigneCommande()))
                .build();

    }

    public static CommandeResponseDto entityToDtoDepotDestination(Commande commande) {
        return CommandeResponseDto.builder()
                .id(commande.getId())
                .code(commande.getCode())
                .idDepot(commande.getDepot().getId())
                .idDepotDestination(commande.getDepot().getId())
                .pt(commande.getPt())
                .statut(commande.getStatut())
                .document(commande.getDocument())
                .type(commande.getType())
                .fournisseur(FournisseurResponseDto.entityToDto(commande.getFournisseur()))
                .client(ClientResponseDto.entityToDto(commande.getClient()))
                .date_creation(commande.getDate_creation())
                //.ligneCommandes(LigneCommandeResponseDto.entityToDtoList(commande.getLigneCommande()))
                .build();

    }

    public static CommandeResponseDto entityToDtoWithLigneCommande(Commande commande) {
        return CommandeResponseDto.builder()
                .id(commande.getId())
                .code(commande.getCode())
                .idDepot(commande.getDepot().getId())
                .statut(commande.getStatut())
                .pt(commande.getPt())
                .document(commande.getDocument())
                .type(commande.getType())
                .fournisseur(FournisseurResponseDto.entityToDto(commande.getFournisseur()))
                .client(ClientResponseDto.entityToDto(commande.getClient()))
                .ligneCommandes(LigneCommandeResponseDto.entityToDtoList(commande.getLigneCommande()))
                .date_creation(commande.getDate_creation())
                .build();

    }

    public static CommandeResponseDto entityToDto(Commande commande, List<LigneCommande> ligneCommandes) {
        return CommandeResponseDto.builder()
                .id(commande.getId())
                .code(commande.getCode())
                .idDepot(commande.getDepot().getId())
                .statut(commande.getStatut())
                .pt(commande.getPt())
                .document(commande.getDocument())
                .type(commande.getType())
                .fournisseur(FournisseurResponseDto.entityToDto(commande.getFournisseur()))
                .client(ClientResponseDto.entityToDto(commande.getClient()))
                .ligneCommandes(LigneCommandeResponseDto.entityToDtoList(ligneCommandes))
                .date_creation(commande.getDate_creation())
                .build();

    }
public static CommandeResponseDto entityToDtoDepot(Commande commande, List<LigneCommande> ligneCommandes, Long idDepotDestination) {
        return CommandeResponseDto.builder()
                .id(commande.getId())
                .code(commande.getCode())
                .idDepot(commande.getDepot().getId())
                .idDepotDestination(idDepotDestination)
                .statut(commande.getStatut())
                .pt(commande.getPt())
                .document(commande.getDocument())
                .type(commande.getType())
                .fournisseur(FournisseurResponseDto.entityToDto(commande.getFournisseur()))
                .client(ClientResponseDto.entityToDto(commande.getClient()))
                .ligneCommandes(LigneCommandeResponseDto.entityToDtoList(ligneCommandes))
                .date_creation(commande.getDate_creation())
                .build();

    }

    public static List<CommandeResponseDto> entityToDtoList(List<Commande> commandes) {
        return commandes.stream().map(CommandeResponseDto::entityToDto).collect(Collectors.toList());
    }

    public static Page<CommandeResponseDto> entityToDtoList(Page<Commande> commandeList){
        return commandeList.map(CommandeResponseDto::entityToDto);
    }

}
