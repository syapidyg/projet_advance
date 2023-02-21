package com.advance.pharmacie.dto.dtoResponse;

import com.advance.pharmacie.model.Client;
import com.advance.pharmacie.model.Commande;
import com.advance.pharmacie.model.Fournisseur;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommandeResponseDto {

    private Long id;
    private String type;
    private String statut;
    private FournisseurResponseDto fournisseur;
    private ClientResponseDto client;

    public static CommandeResponseDto entityToDto(Commande commande) {
        return CommandeResponseDto.builder()
                .id(commande.getId())
                .statut(commande.getStatut())
                .type(commande.getType())
                .fournisseur(FournisseurResponseDto.entityToDto(commande.getFournisseur()))
                .client(ClientResponseDto.entityToDto(commande.getClient()))
                .build();

    }
}
