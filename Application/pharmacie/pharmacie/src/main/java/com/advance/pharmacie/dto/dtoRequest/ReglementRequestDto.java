package com.advance.pharmacie.dto.dtoRequest;


import com.advance.pharmacie.model.Caisse;
import com.advance.pharmacie.model.Commande;
import com.advance.pharmacie.model.lnk.Reglement;
import com.advance.pharmacie.model.auth.Utilisateur;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ReglementRequestDto {

    private Long id;
    private Long idUtilisateur;
    private Long idCommande;
    private Long idCaisse;
    private Long montant;
    private Long rendu;
    private Long reste;

    public static Reglement dtoToEntity(ReglementRequestDto dto, Utilisateur utilisateur, Caisse caisse, Commande commande) {
        return Reglement.ReglementBuilder.aReglement()
                .id(dto.getId())
                .utilisateur(utilisateur)
                .caisse(caisse)
                .commande(commande)
                .montant(dto.getMontant())
                .rendu(dto.getRendu())
                .reste(dto.getReste())
                .build();
    }

}
