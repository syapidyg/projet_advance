package com.advance.pharmacie.dto.dtoRequest;

import com.advance.pharmacie.model.Client;
import com.advance.pharmacie.model.Commande;
import com.advance.pharmacie.model.Fournisseur;
import lombok.Builder;
import lombok.Data;

import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class CommandeRequestDto {

    private Long id;
    private Long pt;
    private String type;
    private String statut;
    private Long idClientFournisseur;
    private List<LigneCommandeRequestDto> LigneCommandes = new ArrayList<>();

    public static Commande dtoToEntity(CommandeRequestDto dto, Client client, Fournisseur fournisseur) {
        return Commande.CommandeBuilder.aCommande()
                .id(dto.getId())
                .statut(dto.getStatut())
                .fournisseur(fournisseur)
                .pt(dto.getPt())
                .client(client)
                .build();

    }
}
