package com.advance.pharmacie.dto.dtoRequest;

import com.advance.pharmacie.model.Client;
import com.advance.pharmacie.model.Commande;
import com.advance.pharmacie.model.Depot;
import com.advance.pharmacie.model.Fournisseur;
import com.advance.pharmacie.util.StatutCommande;
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
    private String code;
    private Long pt;
    private String type;
    private String document;
    private Long idDepot;
    private Long idDepotDestination;
    private Long idClientFournisseur;
    private List<LigneCommandeRequestDto> LigneCommandes = new ArrayList<>();

    public static Commande dtoToEntity(CommandeRequestDto dto, Client client, Fournisseur fournisseur, Depot depot) {

        StatutCommande statutToSave;
        switch (dto.getDocument()) {
            case "Bon de commande":
                statutToSave = StatutCommande.EN_ATTENTE;
                break;
            case "Facture":
                statutToSave = StatutCommande.NON_REGLE;
                break;
            case "Entree en stock":
                statutToSave = StatutCommande.ENTREE;
                break;
            default:
                statutToSave = StatutCommande.TRANSFERT;
                break;
        }

        return Commande.CommandeBuilder.aCommande()
                .id(dto.getId())
                .statut(statutToSave)
                .document(dto.getDocument())
                .type(dto.getType())
                .fournisseur(fournisseur)
                .depot(depot)
                .pt(dto.getPt())
                .client(client)
                .build();

    }
}
