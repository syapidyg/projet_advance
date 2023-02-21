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
    private Long idFournisseur;
    private Long idClient;

    public Commande entityToDto(CommandeResponseDto dto, Client client, Fournisseur fournisseur) {
        return Commande.CommandeBuilder.aCommande()
                .id(dto.getId())
                .statut(dto.getStatut())
                .type(dto.getType())
                .fournisseur(fournisseur)
                .client(client)
                .build();

    }
}
