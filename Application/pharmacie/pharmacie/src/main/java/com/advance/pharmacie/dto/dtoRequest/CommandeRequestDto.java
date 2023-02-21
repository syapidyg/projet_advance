package com.advance.pharmacie.dto.dtoRequest;

import com.advance.pharmacie.model.Client;
import com.advance.pharmacie.model.Commande;
import com.advance.pharmacie.model.Fournisseur;
import lombok.Builder;
import lombok.Data;

import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Builder
public class CommandeRequestDto {

    private Long id;
    private String type;
    private String statut;
    private Long idFournisseur;
    private Long idClient;

    public static Commande dtoToEntity(CommandeRequestDto dto, Client client, Fournisseur fournisseur) {
        return Commande.CommandeBuilder.aCommande()
                .id(dto.getId())
                .statut(dto.getStatut())
                .type(dto.getType())
                .fournisseur(fournisseur)
                .client(client)
                .build();

    }
}
