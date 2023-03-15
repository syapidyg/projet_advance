package com.advance.pharmacie.dto.dtoResponse;

import com.advance.pharmacie.model.Client;
import com.advance.pharmacie.model.Commande;
import com.advance.pharmacie.model.Fournisseur;
import com.advance.pharmacie.model.lnk.LigneCommande;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class CommandeResponseDto {

    private Long id;
    private Long pt;
    private String type;
    private String statut;
    private FournisseurResponseDto fournisseur;
    private ClientResponseDto client;
    private List<LigneCommandeResponseDto> ligneCommandes;

    public static CommandeResponseDto entityToDto(Commande commande) {
        return CommandeResponseDto.builder()
                .id(commande.getId())
                .pt(commande.getPt())
                .statut(commande.getStatut())
                .type(commande.getType())
                .fournisseur(FournisseurResponseDto.entityToDto(commande.getFournisseur()))
                .client(ClientResponseDto.entityToDto(commande.getClient()))
                //.ligneCommandes(LigneCommandeResponseDto.entityToDtoList(commande.getLigneCommande()))
                .build();

    }
    public static CommandeResponseDto entityToDtoWithLigneCommande(Commande commande) {
        return CommandeResponseDto.builder()
                .id(commande.getId())
                .statut(commande.getStatut())
                .type(commande.getType())
                .fournisseur(FournisseurResponseDto.entityToDto(commande.getFournisseur()))
                .client(ClientResponseDto.entityToDto(commande.getClient()))
                .ligneCommandes(LigneCommandeResponseDto.entityToDtoList(commande.getLigneCommande()))
                .build();

    }

    public static CommandeResponseDto entityToDto(Commande commande, List<LigneCommande> ligneCommandes) {
        return CommandeResponseDto.builder()
                .id(commande.getId())
                .statut(commande.getStatut())
                .type(commande.getType())
                .fournisseur(FournisseurResponseDto.entityToDto(commande.getFournisseur()))
                .client(ClientResponseDto.entityToDto(commande.getClient()))
                .ligneCommandes(LigneCommandeResponseDto.entityToDtoList(ligneCommandes))
                .build();

    }

    public static List<CommandeResponseDto> entityToDtoList(List<Commande> commandes) {
        return commandes.stream().map(CommandeResponseDto::entityToDto).collect(Collectors.toList());
    }

}
