package com.advance.pharmacie.dto.dtoRequest;


import com.advance.pharmacie.model.Caisse;
import com.advance.pharmacie.model.Commande;
import com.advance.pharmacie.model.lnk.Reglement;
import com.advance.pharmacie.model.auth.Utilisateur;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReglementRequestDto {


    private Long id;
    private Long idUtilisateur;
    private Long idCommande;
    private Long idCaisse;
    private Long Montant;
    private String date;

    public static Reglement dtoToEntity(ReglementRequestDto dto, Utilisateur utilisateur, Caisse caisse, Commande commande) {
        return Reglement.ReglementBuilder.aReglement()
                .id(dto.getId())
                .utilisateur(utilisateur)
                .caisse(caisse)
                .commande(commande)
                .montant(dto.getMontant())
                .date(dto.getDate())
                .build();
    }

}
