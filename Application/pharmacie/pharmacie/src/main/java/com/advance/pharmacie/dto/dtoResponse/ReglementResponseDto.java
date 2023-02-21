package com.advance.pharmacie.dto.dtoResponse;


import com.advance.pharmacie.model.Caisse;
import com.advance.pharmacie.model.Commande;
import com.advance.pharmacie.model.Reglement;
import com.advance.pharmacie.model.Utilisateur;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReglementResponseDto {


    private Long id;
    private Utilisateur utilisateur;
    private Long idCommande;
    private Long idCaisse;

    public Reglement entityToDto(ReglementResponseDto dto, Utilisateur utilisateur, Caisse caisse, Commande commande) {
        return Reglement.ReglementBuilder.aReglement()
                .id(dto.getId())
                .utilisateur(utilisateur)
                .caisse(caisse)
                .commande(commande)
                .build();
    }

}
